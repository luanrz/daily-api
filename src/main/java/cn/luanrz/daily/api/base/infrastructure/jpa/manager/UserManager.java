package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserWechat;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.UserAuthRepository;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.UserWechatRepository;
import org.springframework.stereotype.Service;

/**
 * 用户Manager
 */
@Service
public class UserManager {

    /** 用户认证Repository */
    private UserAuthRepository userAuthRepository;
    /** 用户微信Repository */
    private UserWechatRepository userWechatRepository;

    public UserManager(UserAuthRepository userAuthRepository, UserWechatRepository userWechatRepository){
        this.userAuthRepository = userAuthRepository;
        this.userWechatRepository = userWechatRepository;
    }

    /**
     * 根据用户名和密码查找用户认证信息
     * @param username 用户名
     * @param password 密码
     * @return 用户认证对象
     */
    public UserAuth findUserAuthByUsernameAndPassword(String username, String password){
        return userAuthRepository.findUserAuthByUsernameAndPassword(username,password);
    }

    /**
     * 根据微信ID查找微信用户认证信息
     * @param wechatId 微信Id（openId）
     * @return 用户微信对象
     */
    public UserWechat findWechatUserAuthByWechatId(String wechatId){
        return userWechatRepository.findUserWechatByWechatId(wechatId);
    }

    /**
     * 根据用户Id查找用户认证信息
     * @param userId 用户Id
     * @return 用户认证对象
     */
    public UserAuth findUserAuthByUserId(String userId) {
        return userAuthRepository.findUserAuthByUserId(userId);
    }

    /**
     * 用户注册
     * @param userAuth 用户认证对象
     * @return 用户认证对象
     */
    public UserAuth register(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }

    /**
     * 用户绑定微信
     * @param userWechat 用户微信信息
     * @return 用户微信信息
     */
    public UserWechat bindWechat(UserWechat userWechat) {
        return userWechatRepository.save(userWechat);
    }
}
