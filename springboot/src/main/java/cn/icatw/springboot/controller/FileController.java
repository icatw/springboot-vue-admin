package cn.icatw.springboot.controller;

import cn.icatw.springboot.service.SysFileService;
import cn.icatw.springboot.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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
    //@PostMapping("/upload")
    //public String upload(@RequestParam MultipartFile file) {
    //    //原始名称
    //    String originalFilename = file.getOriginalFilename();
    //    //文件类型（后缀名）
    //    String type = FileUtil.extName(originalFilename);
    //    long size = file.getSize();
    //    //    先存储到磁盘，再存储到数据库
    //}

    /**
     * 读取配置文件中的路径 static/files
     */
    @Value("${fileLocation}")
    private String fileLocation;
    @Autowired
    private SysFileService fileService;
    @Autowired
    private SysUserService userService;
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public void upload(@RequestParam MultipartFile file) throws IOException {
        log.error("hhhhhh");
        // 获得 classpath 的绝对路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + fileLocation;
        log.info("文件路径为++++++++++++++===================" + realPath);
        File newFile = new File(fileLocation);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        // 上传
        Date date = new Date();
        String fileName = date.getTime() + "@" + file.getOriginalFilename();
        log.info("文件名为++++++++++++++===================" + fileName);
        file.transferTo(new File(newFile, fileName));

    }

}
