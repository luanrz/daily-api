package cn.luanrz.daily.api.base.exception.error;

/**
 * 操作记录模块错误枚举
 */
public enum OperationErrorEnum implements IErrorGetter{
    INCORRECT_OPERATION_ID(Error.getInstance("03001","操作记录ID有误")),
    INCORRECT_OPERATION_TYPE(Error.getInstance("03002","操作记录Type有误")),
    INCORRECT_OPERATION_CONTENT(Error.getInstance("03003","操作记录content有误"));

    private Error error;
    OperationErrorEnum(Error error){
        this.error = error;
    }
    @Override
    public Error getError() {
        return error;
    }
}
