package cn.luanrz.daily.api.base.exception.error;

/**
 * 用户模块错误枚举
 */
public enum UserErrorEnum implements IErrorGetter{
    AUTH_FAIL_USERNAME_PASSWORD(Error.getInstance("010001","用户名或密码错误")),
    AUTH_FAIL_WECHAT_CODE(Error.getInstance("010002","WechatCode有误"));

    private Error error;
    UserErrorEnum(Error error){
        this.error = error;
    }
    @Override
    public Error getError() {
        return error;
    }
}