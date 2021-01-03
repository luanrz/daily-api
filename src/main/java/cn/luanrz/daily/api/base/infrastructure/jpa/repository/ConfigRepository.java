package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 配置Repository
 */
@Repository
public interface ConfigRepository extends CrudRepository<Config,String> {
}
