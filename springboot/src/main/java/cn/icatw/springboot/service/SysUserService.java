package cn.icatw.springboot.service;

import cn.icatw.springboot.dto.UserDto;
import cn.icatw.springboot.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (SysUser)表服务接口
 *
 * @author icatw
 * @since 2022-04-26 08:37:23
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询
     *  @param pageNum  页面num
     * @param pageSize 页面大小
     * @param username 用户名
     * @param email 邮箱
     * @param address  地址
     * @return
     */
    IPage<SysUser> getPage(Integer pageNum, Integer pageSize, String username, String email, String address);

    /**
     * 登录
     *
     * @param userDto 用户dto
     * @return boolean
     */
    UserDto login(UserDto userDto);

    /**
     * 注册
     *
     * @param userDto 用户dto
     * @return {@link UserDto}
     */
    boolean register(UserDto userDto);

    /**
     * 得到用户名
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    SysUser getByUsername(String username);
}

