package cn.luanrz.daily.api.moudle.task.extend;

import cn.luanrz.daily.api.base.common.IdGenerator;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.common.OperationContentParser;
import cn.luanrz.daily.api.moudle.operation.OperationService;
import cn.luanrz.daily.api.moudle.operation.common.OperationTypeEnum;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 任务扩展服务实现
 */
@Service
public class TaskExtendServiceImpl implements TaskExtendService {
    private final OperationService operationService;

    public TaskExtendServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void syncOperation(OperationTypeEnum operationTypeEnum, Task task) {
        Operation operation = new Operation()
                .setType(operationTypeEnum.name())
                .setContent(OperationContentParser.toJson(task))
                .setUserId(task.getUserId())
                .setOperationId(IdGenerator.generateUUID())
                .setOperateTime(new Date());
        operationService.add(operation);
    }
}
