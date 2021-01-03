package cn.luanrz.daily.api.base.exception;

import cn.luanrz.daily.api.base.exception.error.Error;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = DailyException.class)
    @ResponseBody
    public Error handleException(DailyException e) {
        return e.getError();
    }
}
