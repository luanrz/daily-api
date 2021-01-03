package cn.luanrz.daily.api.base.http.wechat.dto;

public class WechatAuthResponse {
    /** 用户唯一标识 */
    private String openid;
    /** 会话密钥 */
    private String session_key;
    /** 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回， */
    private String unionid;
    /** 错误码 */
    private String errcode;
    /** 错误信息 */
    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public WechatAuthResponse(String openid, String session_key, String unionid, String errcode, String errmsg) {
        this.openid = openid;
        this.session_key = session_key;
        this.unionid = unionid;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public WechatAuthResponse() {
    }
}