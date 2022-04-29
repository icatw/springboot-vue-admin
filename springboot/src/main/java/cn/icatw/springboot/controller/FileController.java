package cn.icatw.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.icatw.springboot.common.Result;
import cn.icatw.springboot.entity.SysFile;
import cn.icatw.springboot.service.SysFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件控制器
 *
 * @author icatw
 * @date 2022/4/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    /**
     * 读取配置文件中的路径 static/files
     */
    @Value("${fileLocation}")
    private String fileUploadPath;
    @Autowired
    private SysFileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        SysFile dbFiles = getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/file/" + fileUUID;
        }

        // 存储数据库
        SysFile saveFile = new SysFile();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileService.save(saveFile);

        return url;
    }

    /**
     * 下载
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     *
     * @param fileUUID 文件uuid
     * @param response 响应
     * @throws IOException
     */
    @ApiOperation(value = "文件下载")
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


    /**
     * 得到文件md5
     * 通过文件的md5查询文件
     *
     * @param md5 md5
     * @return {@link SysFile}
     */
    private SysFile getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<SysFile> filesList = fileService.list(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    /**
     * 更新
     *
     * @param files 文件
     * @return {@link Result}
     */
    @ApiOperation(value = "文件更新")
    @PostMapping("/update")
    public Result update(@RequestBody SysFile files) {
        return Result.success(fileService.updateById(files));
    }

    /**
     * 删除
     *
     * @param id id
     * @return {@link Result}
     */
    @ApiOperation(value = "文件删除（逻辑删除）")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        SysFile files = fileService.getById(id);
        files.setIsDelete(1);
        fileService.updateById(files);
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids id
     * @return {@link Result}
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<SysFile> files = fileService.list(queryWrapper);
        for (SysFile file : files) {
            file.setIsDelete(1);
            fileService.updateById(file);
        }
        return Result.success();
    }

    /**
     * 分页查询接口
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link Result}
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        Page<SysFile> page = fileService.page(new Page<>(pageNum, pageSize), queryWrapper);
        log.error(page.toString());
        return Result.success(page);
    }
}


