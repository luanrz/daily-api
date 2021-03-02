package cn.luanrz.daily.api.base.exception.error;

/**
 * 任务模块错误枚举
 */
public enum TaskErrorEnum implements IErrorGetter{
    INCORRECT_TASK_ID(Error.getInstance("02001","任务ID有误"));

    private Error error;
    TaskErrorEnum(Error error){
        this.error = error;
    }
    @Override
    public Error getError() {
        return error;
    }
}
