package cn.luanrz.daily.api.moudle.user;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserDetail;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 根据用户名和密码登录
     * @param userAuth 包含用户名和密码的用户认证对象
     * @return 完整的用户认证对象
     */
    UserDetail loginByUsername(UserAuth userAuth);

    /**
     * 注册
     * @param userAuth 包含用户名和密码的用户认证对象
     * @return 用户认证对象
     */
    UserDetail register(UserAuth userAuth);

    /**
     * 根据微信code登录
     * @param wechatCode 微信code（在微信小程序端调用wx.login方法获取）
     * @return 完整的用户对象
     */
    UserDetail loginByWechatCode(String wechatCode);
}
