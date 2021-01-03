package cn.luanrz.daily.api.moudle.user;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.UserErrorEnum;
import cn.luanrz.daily.api.base.http.wechat.WechatAuthApiClient;
import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthRequest;
import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthResponse;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserWechat;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.ConfigManager;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.UserManager;
import cn.luanrz.daily.api.base.common.IdGenerator;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 */
@Service
public class UserService {

    private final UserManager userManager;
    private final ConfigManager configManager;

    UserService(UserManager userManager, ConfigManager configManager){
        this.userManager = userManager;
        this.configManager = configManager;
    }
    /**
     * 根据用户名和密码登录
     * @param userAuth 包含用户名和密码的用户认证对象
     * @return 完整的用户认证对象
     */
    public UserAuth loginByUsername(UserAuth userAuth) {
        UserAuth newUserAuth = userManager.findUserAuthByUsernameAndPassword(userAuth.getUsername(), userAuth.getPassword());
        if (newUserAuth == null){
            throw DailyException.getInstance(UserErrorEnum.AUTH_FAIL_USERNAME_PASSWORD);
        }
        return newUserAuth;
    }

    /**
     * 注册
     * @param userAuth 包含用户名和密码的用户认证对象
     * @return 用户认证对象
     */
    public UserAuth register(UserAuth userAuth) {
        //UserId也可由数据库自动生成
        userAuth.setUserId(IdGenerator.generateUUID());
        return userManager.register(userAuth);
    }

    /**
     * 根据微信code登录
     * @param wechatCode 微信code（在微信小程序端调用wx.login方法获取）
     * @return 完整的用户对象
     */
    public UserAuth loginByWechatCode(String wechatCode){
        //向微信服务器发送登录请求，以获取用户openid
        WechatAuthRequest wechatAuthRequest = WechatAuthRequest.builder()
                .setAppid(configManager.findConfig(ConfigManager.CONFIG_KEY_APPID))
                .setSecret(configManager.findConfig(ConfigManager.CONFIG_KEY_SECRET))
                .setCode(wechatCode).build();
        WechatAuthResponse wechatAuthResponse = WechatAuthApiClient.auth(wechatAuthRequest);
        String openid = wechatAuthResponse.getOpenid();

        if (null == openid){
            throw DailyException.getInstance(UserErrorEnum.AUTH_FAIL_WECHAT_CODE);
        }

        //根据微信id获取用户微信信息
        UserWechat userWechat = userManager.findWechatUserAuthByWechatId(openid);

        //如果此微信用户已注册，直接登录
        if (null != userWechat){
            return userManager.findUserAuthByUserId(userWechat.getUserId());
        }

        //微信用户自动注册
        UserAuth userAuth = registerByWechat();
        //绑定微信
        bindWechatId(new UserWechat(userAuth.getUserId(),openid));
        return userAuth;
    }


    /**
     * 微信用户自动注册
     * @return 用户认证对象
     */
    public UserAuth registerByWechat() {
        String id = IdGenerator.generateUUID();
        String username = IdGenerator.getWechatUsername(id);
        UserAuth userAuth = new UserAuth(id, username, null);
        return userManager.register(userAuth);
    }
    /**
     * 绑定微信
     * @param userWechat 用户微信信息
     * @return 用户微信对象
     */
    private UserWechat bindWechatId(UserWechat userWechat) {
        return userManager.bindWechat(userWechat);
    }
}
