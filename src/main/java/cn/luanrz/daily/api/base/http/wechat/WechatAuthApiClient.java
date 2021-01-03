package cn.luanrz.daily.api.base.http.wechat;

import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthRequest;
import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthResponse;
import feign.Feign;
import feign.jackson.JacksonDecoder;

/**
 * 微信验证API客户端
 */
public class WechatAuthApiClient {

    /** 微信API地址 */
    private static final String WECHAT_API_URL = "https://api.weixin.qq.com";

    public static WechatAuthResponse auth(WechatAuthRequest request){
        WechatAuthApi wechatAuthApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(WechatAuthApi.class, WECHAT_API_URL);
        return wechatAuthApi.auth(request.getAppid(), request.getSecret(), request.getCode());
    }
}
