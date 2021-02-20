package cn.luanrz.daily.api.moudle.operation.extend;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;

import java.util.List;

/**
 * 操作记录扩展服务
 */
public interface OperationExtendService {
    /**
     * 增加操作记录时, 同步更新任务
     * @param operations 操作记录列表
     */
    void syncTasks(List<Operation> operations);

}
