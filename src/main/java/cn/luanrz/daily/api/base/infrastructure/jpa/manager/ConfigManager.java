package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.GlobalErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Config;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 配置Manager
 */
@Service
public class ConfigManager {

    public static final String CONFIG_KEY_APPID = "appid";
    public static final String CONFIG_KEY_SECRET = "secret";

    /** 配置Repository */
    private final ConfigRepository configRepository;

    public ConfigManager(ConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    /**
     * 查找配置
     * @param key 键
     * @return 值
     */
    public String findConfig(String key){
        Optional<Config> configOptional = configRepository.findById(key);
        if (configOptional.isPresent()){
            return configOptional.get().getValue();
        }
        throw DailyException.getInstance(GlobalErrorEnum.CONFIG_FIND_EMPTY);
    }
}
