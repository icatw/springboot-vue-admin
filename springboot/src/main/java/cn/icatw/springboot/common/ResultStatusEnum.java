package cn.icatw.springboot.common;

/**
 * @author icatw
 * @date 2022/4/28
 * @email 762188827@qq.com
 * @apiNote
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应结果状态枚举类
 *
 * @author icatw
 * @date 2022/04/28
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),

    /**
     * 密码错误
     */
    PASSWORD_NOT_MATCHING(300, "用户名或密码错误"),
    /**
     * 用户存在
     */
    USER_EXISTS(301, "该用户名已存在"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(401, "系统错误"),
    /**
     * 其他异常
     */
    OTHER_EXCEPTION(500, "其他错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}

