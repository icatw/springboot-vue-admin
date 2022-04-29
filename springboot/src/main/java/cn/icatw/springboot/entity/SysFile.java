package cn.icatw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * sys文件
 *
 * @author icatw
 * @TableName sys_file
 * @date 2022/04/28
 */
@TableName(value = "sys_file")
@Data
public class SysFile implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 下载链接
     */
    private String url;

    /**
     * 逻辑删除(0,未删除,1,删除)
     */
    private Integer isDelete;

    /**
     * 是否禁用（1，可用，0，禁用）
     */
    private boolean enable;

    private String md5;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", size=").append(size);
        sb.append(", url=").append(url);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", enable=").append(enable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
