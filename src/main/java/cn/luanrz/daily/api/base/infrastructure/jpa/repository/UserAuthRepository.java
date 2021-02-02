package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户认证Repository
 */
@Repository
public interface UserAuthRepository extends CrudRepository<UserAuth,String> {

    /**
     * 根据用户名和密码查找用户认证信息
     * @param username 用户名
     * @param password 密码
     * @return 用户认证信息
     */
    UserAuth findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户Id查找用户认证信息
     * @param userId 用户Id
     * @return 用户认证信息
     */
    UserAuth findByUserId(String userId);

}
