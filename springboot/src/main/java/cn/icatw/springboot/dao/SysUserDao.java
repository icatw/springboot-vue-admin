package cn.icatw.springboot.dao;

import cn.icatw.springboot.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysUser)表数据库访问层
 *
 * @author icatw
 * @since 2022-04-26 08:37:23
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}

