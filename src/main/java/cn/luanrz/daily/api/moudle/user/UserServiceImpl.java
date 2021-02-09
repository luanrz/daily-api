package cn.luanrz.daily.api.moudle.user;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.UserErrorEnum;
import cn.luanrz.daily.api.base.http.wechat.WechatAuthApiClient;
import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthRequest;
import cn.luanrz.daily.api.base.http.wechat.dto.WechatAuthResponse;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserDetail;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserWechat;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.ConfigManager;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.UserManager;
import cn.luanrz.daily.api.base.common.IdGenerator;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserManager userManager;
    private final ConfigManager configManager;

    UserServiceImpl(UserManager userManager, ConfigManager configManager){
        this.userManager = userManager;
        this.configManager = configManager;
    }

    @Override
    public UserDetail loginByUsername(UserAuth userAuth) {
        UserAuth newUserAuth = userManager.findUserAuthByUsernameAndPassword(userAuth.getUsername(), userAuth.getPassword());
        if (newUserAuth == null){
            throw DailyException.getInstance(UserErrorEnum.AUTH_FAIL_USERNAME_PASSWORD);
        }
        return userManager.findUserDetailByUserId(newUserAuth.getUserId());
    }

    @Override
    public UserDetail register(UserAuth userAuth) {
        userAuth.setUserId(IdGenerator.generateUUID());
        UserAuth newUserAuth = userManager.register(userAuth);
        return userManager.findUserDetailByUserId(newUserAuth.getUserId());
    }

    @Override
    public UserDetail loginByWechatCode(String wechatCode){
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

        UserAuth userAuth;

        //根据微信id获取用户微信信息
        UserWechat userWechat = userManager.findWechatUserAuthByWechatId(openid);

        if (null != userWechat){
            //如果此微信用户已注册，直接登录
            userAuth = userManager.findUserAuthByUserId(userWechat.getUserId());
        }else {
            //微信用户自动注册
            userAuth = registerByWechat();
            //绑定微信
            bindWechatId(new UserWechat(userAuth.getUserId(),openid));
        }
        return userManager.findUserDetailByUserId(userAuth.getUserId());
    }


    /**
     * 微信用户自动注册
     * @return 用户认证对象
     */
    private UserAuth registerByWechat() {
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
