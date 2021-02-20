package cn.luanrz.daily.api.moudle.task.extend;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.common.OperationTypeEnum;

/**
 * 任务扩展服务
 */
public interface TaskExtendService {
    /**
     * 变更任务时, 同步更新操作记录
     * @param operationTypeEnum 变更类型(包括任务及任务列表的增删改)
     * @param task 待变更的任务
     */
    void syncOperation(OperationTypeEnum operationTypeEnum, Task task);
}
