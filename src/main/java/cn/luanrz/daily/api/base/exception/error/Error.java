package cn.luanrz.daily.api.base.exception.error;

/**
 * 错误信息
 */
public class Error {
    /** 错误编码 */
    private String errorCode;
    /** 错误信息 */
    private String errorMsg;

    public static Error getInstance(String errorCode, String errorMsg){
        return new Error(errorCode, errorMsg);
    }

    public Error(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Error() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
