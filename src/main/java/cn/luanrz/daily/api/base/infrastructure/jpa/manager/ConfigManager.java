package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.infrastructure.jpa.repository.ConfigRepository;
import org.springframework.stereotype.Service;

/**
 * 配置Manager
 */
@Service
public class ConfigManager {

    public static final String CONFIG_KEY_APPID = "appid";
    public static final String CONFIG_KEY_SECRET = "secret";

    /** 配置Repository */
    private ConfigRepository configRepository;

    public ConfigManager(ConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    /**
     * 查找配置
     * @param key 键
     * @return 值
     */
    public String findConfig(String key){
        return configRepository.findById(key).get().getValue();
    }
}
