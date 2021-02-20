package cn.luanrz.daily.api.moudle.operation.extend;


import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.common.OperationContentParser;
import cn.luanrz.daily.api.moudle.operation.common.OperationTypeEnum;
import cn.luanrz.daily.api.moudle.task.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作记录扩展服务实现
 */
@Service
public class OperationExtendServiceImpl implements OperationExtendService {

    private final TaskService taskService;

    public OperationExtendServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void syncTasks(List<Operation> operations) {
        operations.forEach(this::syncTask);
    }

    private void syncTask(Operation operation) {
        String type = operation.getType();
        String userId = operation.getUserId();
        String content = operation.getContent();
        Task task = OperationContentParser.toObject(content);
        task.setUserId(userId);

        if (OperationTypeEnum.ADD_TASK.name().equalsIgnoreCase(type)) {
            taskService.add(task);
        }
        if (OperationTypeEnum.UPDATE_TASK.name().equalsIgnoreCase(type)) {
            taskService.update(task);
        }
        if (OperationTypeEnum.DELETE_TASK.name().equalsIgnoreCase(type)) {
            taskService.delete(task);
        }
    }
}
