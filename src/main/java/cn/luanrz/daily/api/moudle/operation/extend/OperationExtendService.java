package cn.luanrz.daily.api.moudle.operation.extend;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.OperationTypeEnum;

import java.util.List;

/**
 * 操作记录扩展服务
 */
public interface OperationExtendService {
    /**
     * 增加操作记录时, 同步更新任务
     * @param operations 操作记录列表
     * @return 同步更新后的任务列表
     */
    List<Task> syncTask(List<Operation> operations);

    /**
     * 变更任务时, 同步更新操作记录
     * @param operationTypeEnum 变更类型(包括任务及任务列表的增删改)
     * @param task 待变更的任务
     * @return 同步更新后的操作记录
     */
    Operation syncOperation(OperationTypeEnum operationTypeEnum, Task task);
}
