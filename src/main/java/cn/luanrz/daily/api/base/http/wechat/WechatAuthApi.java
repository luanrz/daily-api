package cn.luanrz.daily.api.base.http.wechat;

import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 微信验证API
 */
public interface WechatAuthApi {
    /**
     * 微信验证
     * @param appid 小程序ID
     * @param secret 小程序密钥
     * @param code 小程序端执行wx.login时获取的 code
     * @return 验证结果WechatAuthResponse对象
     */
    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code")
    WechatAuthResponse auth(@Param("appid")String appid, @Param("secret") String secret, @Param("code")String code);
}
