package cn.icatw.springboot.exception;

import cn.icatw.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author icatw
 * @date 2022/4/28
 * @email 762188827@qq.com
 * @apiNote
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Result handleCustomException(CustomException customException) {

        return Result.error(customException.getCode(), customException.getMessage());
    }
}
