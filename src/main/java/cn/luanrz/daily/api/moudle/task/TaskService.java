package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.TaskManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务服务类
 */
@Service
public class TaskService {

    /** 任务Manager */
    private final TaskManager taskManager;

    public TaskService(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * 查找用户的所有任务
     * @param userId 用户id
     * @return 任务列表
     */
    public List<Task> findAll(String userId){
        return taskManager.findAll(userId);
    }

    /**
     * 查找用户的待办事项
     * @param userId 用户id
     * @return 待办事项列表
     */
    public List<Task> findTodo(String userId) {
        String status = "0";
        return taskManager.findTodo(userId, status);
    }
}
