package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户认证信息表
 */
@Entity
@Table(name = "USER_AUTH")
public class UserAuth {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    //序列化时忽略密码
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAuth(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserAuth() {
    }
}
