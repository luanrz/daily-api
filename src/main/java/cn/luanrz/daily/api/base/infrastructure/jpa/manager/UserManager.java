package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.UserErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserDetail;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserWechat;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.UserAuthRepository;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.UserDetailRepository;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.UserWechatRepository;
import org.springframework.stereotype.Service;

/**
 * 用户Manager
 */
@Service
public class UserManager {

    /** 用户认证Repository */
    private final UserAuthRepository userAuthRepository;
    /** 用户微信Repository */
    private final UserWechatRepository userWechatRepository;
    /** 用户详细信息Repository */
    private final UserDetailRepository userDetailRepository;

    public UserManager(UserAuthRepository userAuthRepository, UserWechatRepository userWechatRepository,
                       UserDetailRepository userDetailRepository){
        this.userAuthRepository = userAuthRepository;
        this.userWechatRepository = userWechatRepository;
        this.userDetailRepository = userDetailRepository;
    }

    /**
     * 根据用户名和密码查找用户认证信息
     * @param username 用户名
     * @param password 密码
     * @return 用户认证对象
     */
    public UserAuth findUserAuthByUsernameAndPassword(String username, String password){
        return userAuthRepository.findByUsernameAndPassword(username,password);
    }

    /**
     * 根据微信ID查找微信用户认证信息
     * @param wechatId 微信Id（openId）
     * @return 用户微信对象
     */
    public UserWechat findWechatUserAuthByWechatId(String wechatId){
        return userWechatRepository.findByWechatId(wechatId);
    }

    /**
     * 根据用户Id查找用户认证信息
     * @param userId 用户Id
     * @return 用户认证对象
     */
    public UserAuth findUserAuthByUserId(String userId) {
        return userAuthRepository.findByUserId(userId);
    }

    /**
     * 用户注册
     * @param userAuth 用户认证对象
     * @return 用户认证对象
     */
    public UserAuth register(UserAuth userAuth) {
        UserAuth originalUserAuth = userAuthRepository.findByUserId(userAuth.getUserId());
        if (originalUserAuth == null){
            throw DailyException.getInstance(UserErrorEnum.EXIST_SAME_USERNAME);
        }
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

    /**
     * 查询用户详细信息
     * @param userId 用户id
     * @return 用户详细信息
     */
    public UserDetail findUserDetailByUserId(String userId){
        return userDetailRepository.findByUserId(userId);
    }
}
