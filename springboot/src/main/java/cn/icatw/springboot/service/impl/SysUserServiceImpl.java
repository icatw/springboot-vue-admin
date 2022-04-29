package cn.icatw.springboot.service.impl;

import cn.icatw.springboot.common.ResultStatusEnum;
import cn.icatw.springboot.dto.UserDto;
import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.exception.CustomException;
import cn.icatw.springboot.mapper.SysUserMapper;
import cn.icatw.springboot.service.SysUserService;
import cn.icatw.springboot.utils.TokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;


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
            String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
            dto.setToken(token);

            return dto;
        } else {
            throw new CustomException(ResultStatusEnum.PASSWORD_NOT_MATCHING);
        }
    }

    @Override
    public boolean register(UserDto userDto) {
        SysUser user = this.baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", userDto.getUsername()));
        if (user == null) {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userDto, sysUser);
            //设置默认昵称和头像
            sysUser.setNickname("Tom");
            sysUser.setAvatarUrl("https://q1.qlogo.cn/g?b=qq&nk=762188827&s=100");
            return this.save(sysUser);
        } else {
            throw new CustomException(ResultStatusEnum.USER_EXISTS);
        }
    }

    @Override
    public SysUser getByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().eq("username", username));
    }
}

