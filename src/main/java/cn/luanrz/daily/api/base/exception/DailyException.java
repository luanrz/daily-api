package cn.luanrz.daily.api.base.exception;

import cn.luanrz.daily.api.base.exception.error.Error;
import cn.luanrz.daily.api.base.exception.error.IErrorGetter;

/**
 * 全局异常
 */
public class DailyException extends RuntimeException implements IErrorGetter {

    private Error error;

    public static DailyException getInstance(IErrorGetter errorGetter){
        return new DailyException(errorGetter.getError());
    }

    public DailyException() {
    }

    public DailyException(Error error) {
        this.error = error;
    }

    @Override
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
