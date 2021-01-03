package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户微信信息表
 */
@Entity
@Table(name = "USER_WECHAT")
public class UserWechat {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Column(name = "WECHAT_ID", nullable = false)
    private String wechatId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public UserWechat(String userId, String wechatId) {
        this.userId = userId;
        this.wechatId = wechatId;
    }

    public UserWechat() {
    }
}
