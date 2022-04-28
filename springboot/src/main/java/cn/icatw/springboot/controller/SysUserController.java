package cn.icatw.springboot.controller;


import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.icatw.springboot.common.Result;
import cn.icatw.springboot.common.ResultStatusEnum;
import cn.icatw.springboot.dto.UserDto;
import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author icatw
 * @since 2022-04-26 08:37:23
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("sysUser")
@Slf4j
public class SysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService userService;

    /**
     * 保存
     *
     * @param user 用户
     * @return boolean
     */
    @ApiOperation(value = "新增或修改用户信息")
    @PostMapping
    public boolean save(@RequestBody SysUser user) {
        // 新增或者更新
        return userService.saveOrUpdate(user);
    }

    /**
     * 查询所有数据
     *
     * @return {@link List}<{@link SysUser}>
     */
    @ApiOperation(value = "查询所有用户信息")
    @GetMapping
    public List<SysUser> findAll() {
        return userService.list();
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    /**
     * 批量删除
     *
     * @param ids id
     * @return boolean
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping("/batchDelete/{ids}")
    public boolean batchDelete(@PathVariable List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    /**
     * 分页
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @param username 用户名
     * @param email    电子邮件
     * @param address  地址
     * @return {@link IPage}<{@link SysUser}>
     */
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public IPage<SysUser> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String email,
                                   @RequestParam(defaultValue = "") String address) {
        return userService.getPage(pageNum, pageSize, username, email, address);
        //return userService.page(page, queryWrapper);
    }

    /**
     * 导出用户列表
     *
     * @param map      地图
     * @param request  请求
     * @param response 响应
     */
    @ApiOperation(value = "导出会员列表Excel")
    @GetMapping("/export")
    public void exportUserList(ModelMap map,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        List<SysUser> memberList = userService.list();
        ExportParams params = new ExportParams("用户信息列表", "用户信息列表", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, SysUser.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "用户信息");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }


    /**
     * 导入成员列表
     *
     * @param file 文件
     * @return {@link List}<{@link SysUser}>
     * @throws Exception 异常
     */
    @ApiOperation("从Excel导入会员列表")
    @PostMapping("/import")
    public List<SysUser> importMemberList(@RequestPart("file") MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<SysUser> list = ExcelImportUtil.importExcel(
                file.getInputStream(),
                SysUser.class, params);
        log.info(list.toString());
        userService.saveOrUpdateBatch(list);
        return userService.list();
    }

    /**
     * 登录
     *
     * @param userDto 用户dto
     * @return boolean
     */
    @ApiOperation(value = "用户登陆")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto dto = userService.login(userDto);
        if (dto != null) {
            //HttpSession session = request.getSession();
            //session.setAttribute("userInfo",dto);
            //SysUser userInfo = (SysUser) session.getAttribute("userInfo");
            //log.info(userInfo.toString());
            return Result.success(dto);
        }
        return Result.error(ResultStatusEnum.PASSWORD_NOT_MATCHING.getCode(),
                ResultStatusEnum.PASSWORD_NOT_MATCHING.getMessage());
    }

    /**
     * 登录
     *
     * @param userDto 用户dto
     * @return boolean
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto dto = userService.register(userDto);
    }
}


