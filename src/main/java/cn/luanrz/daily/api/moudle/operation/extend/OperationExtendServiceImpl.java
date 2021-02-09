package cn.luanrz.daily.api.moudle.operation.extend;


import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.OperationTypeEnum;

import java.util.List;

/**
 * 操作记录扩展服务实现
 */
public class OperationExtendServiceImpl implements OperationExtendService {

    @Override
    public List<Task> syncTask(List<Operation> operations) {
        //todo syncTask
        return null;
    }

    @Override
    public Operation syncOperation(OperationTypeEnum operationTypeEnum, Task task) {
        //todo sync operation
        return null;
    }
}
