package cn.luanrz.daily.api.moudle.operation;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;

import java.util.List;

/**
 * 操作记录服务
 */
public interface OperationService {
    /**
     * 查询操作记录
     * @param type 查询类型(all latest)
     * @param currentOperationId 当前操作记录ID(仅type为latest时有效)
     * @param userId 用户ID
     * @return 操作记录列表
     */
    List<Operation> find(String type, String currentOperationId, String userId);

    /**
     * 增加操作记录,并在切面中同步更新任务
     * @param operations 待入库的操作记录列表
     * @return 完整的操作记录列表
     */
    List<Operation> add(List<Operation> operations);

    /**
     * 增加操作记录
     * @param operation 待入库的操作记录
     * @return 完整的操作记录
     */
    Operation add(Operation operation);
}
