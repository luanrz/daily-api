package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 操作记录Repository
 */
@Repository
public interface OperationRepository  extends CrudRepository<Operation,String> {

    /**
     * 根据用户Id与操作记录Id查询操作记录
     * @param userId 用户ID
     * @param operationId 操作记录ID
     * @return 操作记录
     */
    Operation findByUserIdAndOperationId(String userId, String operationId);

    /**
     * 根据用户Id查找所有操作记录
     * @param userId 用户Id
     * @return 操作记录列表
     */
    List<Operation> findAllByUserId(String userId);

    /**
     * 查询当前操作记录之后的所有操作记录
     * @param userId 用户ID
     * @param operateTime 操作时间
     * @return 操作记录列表
     */
    List<Operation> findAllByUserIdAndOperateTimeAfter(String userId, Date operateTime);
}
