package cn.luanrz.daily.api.base.exception.error;

/**
 * 全局通用错误枚举
 */
public enum GlobalErrorEnum implements IErrorGetter{
    TOKEN_VERIFY_EMPTY(Error.getInstance("00001","请求Token不存在，请重新登陆")),
    TOKEN_VERIFY_FAIL(Error.getInstance("00002","请求Token验证失败，请重新登陆")),
    CONFIG_FIND_EMPTY(Error.getInstance("00003","根据指定的key找不到对应的配置"));

    private Error error;
    GlobalErrorEnum(Error error){
        this.error = error;
    }
    @Override
    public Error getError() {
        return error;
    }
}
