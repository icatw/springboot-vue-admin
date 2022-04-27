package cn.icatw.springboot.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    /**
     * id
     */
    @Excel(name = "ID", width = 10)
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @Excel(name = "用户名",width = 20,needMerge = true)
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    @Excel(name = "密码",width = 20,needMerge = true)
    private String password;
    /**
     * 昵称
     */
    @Excel(name = "昵称",width = 20,needMerge = true)
    private String nickname;
    /**
     * 电子邮件
     */
    @Excel(name = "邮箱",width = 20,needMerge = true)
    private String email;
    /**
     * 电话
     */
    @Excel(name = "手机号",width = 20,needMerge = true,desensitizationRule = "3_4")
    private String phone;
    /**
     * 地址
     */
    @Excel(name = "地址",width = 20,needMerge = true)
    private String address;
    /**
     * 创建时间
     */
    @Excel(name = "注册时间",width = 20,format = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 头像
     */
    private String avatarUrl;
}

