package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserWechat;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户微信信息Repository
 */
public interface UserWechatRepository extends CrudRepository<UserWechat, String> {
    /**
     * 根据微信Id查找用户微信信息
     * @param wechatId 微信Id（openId）
     * @return 用户微信信息
     */
    UserWechat findByWechatId(String wechatId);
}
