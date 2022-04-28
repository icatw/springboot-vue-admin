package cn.icatw.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果
 * 接口统一返回包装类
 *
 * @author icatw
 * @date 2022/04/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(ResultStatusEnum.SUCCESS.getCode(), "", null);
    }

    public static Result success(Object data) {
        return new Result(ResultStatusEnum.SUCCESS.getCode(), "", data);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result(ResultStatusEnum.SYSTEM_EXCEPTION.getCode(), ResultStatusEnum.SYSTEM_EXCEPTION.getMessage(), null);
    }

}
