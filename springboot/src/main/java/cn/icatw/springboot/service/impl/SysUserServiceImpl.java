package cn.icatw.springboot.service.impl;

import cn.icatw.springboot.dao.SysUserDao;
import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public IPage<SysUser> getPage(Integer pageNum, Integer pageSize, String username, String nickname, String address) {
        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(nickname)) {
            queryWrapper.like("nickname", nickname);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}

