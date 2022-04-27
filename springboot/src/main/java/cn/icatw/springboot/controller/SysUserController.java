package cn.icatw.springboot.controller;


import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

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

}


