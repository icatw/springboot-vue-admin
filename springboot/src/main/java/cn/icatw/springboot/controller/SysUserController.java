package cn.icatw.springboot.controller;


import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author icatw
 * @since 2022-04-26 08:37:23
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService userService;

    // 新增和修改
    @PostMapping
    public boolean save(@RequestBody SysUser user) {
        // 新增或者更新
        return userService.saveOrUpdate(user);
    }

    // 查询所有数据
    @GetMapping
    public List<SysUser> findAll() {
        return userService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }


    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<SysUser> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String nickname,
                                   @RequestParam(defaultValue = "") String address) {
        return userService.getPage(pageNum, pageSize, username, nickname, address);

        //return userService.page(page, queryWrapper);
    }

}


