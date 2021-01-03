package cn.luanrz.daily.api.base.infrastructure.jpa.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 配置表
 */
@Entity
@Table(name = "CONFIG")
public class Config {
    @Id
    @Column(name = "KEY", nullable = false)
    private String key;
    @Column(name = "VALUE", nullable = false)
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Config(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Config() {
    }
}
