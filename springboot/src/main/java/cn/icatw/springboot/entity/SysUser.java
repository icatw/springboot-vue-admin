package cn.icatw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (SysUser)表实体类
 *
 * @author icatw
 * @since 2022-04-26 08:37:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends Model<SysUser> {
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //用户名
    private String username;
    //密码
    @JsonIgnore
    private String password;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //地址
    private String address;
    //创建时间
    private Date createTime;
}

