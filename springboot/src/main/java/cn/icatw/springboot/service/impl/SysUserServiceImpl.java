package cn.icatw.springboot.service.impl;

import cn.icatw.springboot.common.ResultStatusEnum;
import cn.icatw.springboot.dao.SysUserDao;
import cn.icatw.springboot.dto.UserDto;
import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.exception.CustomException;
import cn.icatw.springboot.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysUser)表服务实现类
 *
 * @author icatw
 * @since 2022-04-26 08:37:24
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Autowired
    SysUserDao sysUserDao;


    @Override
    public IPage<SysUser> getPage(Integer pageNum, Integer pageSize, String username, String email, String address) {
        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public UserDto login(UserDto userDto) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDto.getUsername())
                .eq("password", userDto.getPassword());

        SysUser user = null;
        try {
            user = this.baseMapper.selectOne(wrapper);
        } catch (Exception e) {
            throw new CustomException(ResultStatusEnum.SYSTEM_EXCEPTION);
        }
        if (user != null) {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(user, dto);
            return dto;
        } else {
            throw new CustomException(ResultStatusEnum.PASSWORD_NOT_MATCHING);
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        SysUser user = this.baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", userDto.getUsername()));
        if (user != null) {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userDto, sysUser);
            boolean b = this.save(sysUser);
        }else {
            throw new CustomException(ResultStatusEnum)
        }
        return null;
    }
}

