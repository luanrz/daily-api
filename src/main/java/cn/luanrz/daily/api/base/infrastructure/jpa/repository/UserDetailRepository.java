package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户详细信息Repository
 */
@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail,String> {

    /**
     * 根据用户Id查找用户认证信息
     * @param userId 用户Id
     * @return 用户认证信息
     */
    UserDetail findByUserId(String userId);

}
