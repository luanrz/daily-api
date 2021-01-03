package cn.luanrz.daily.api.base.http.wechat.dto;

public class WechatAuthRequest {
    /** 小程序ID */
    private String appid;
    /** 小程序密钥 */
    private String secret;
    /** 小程序端执行wx.login时获取的 code */
    private String code;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String appid;
        private String secret;
        private String code;
        public WechatAuthRequest build(){
            return new WechatAuthRequest(appid, secret, code);
        }
        public Builder setAppid(String appid) {
            this.appid = appid;
            return this;
        }
        public Builder setSecret(String secret) {
            this.secret = secret;
            return this;
        }
        public Builder setCode(String code) {
            this.code = code;
            return this;
        }
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WechatAuthRequest(String appid, String secret, String code) {
        this.appid = appid;
        this.secret = secret;
        this.code = code;
    }

    public WechatAuthRequest() {
    }
}
