package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务Manager
 */
@Service
public class TaskManager {

    /** 任务Repository */
    private TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    /**
     * 查找用户的所有任务
     * @param userId 用户id
     * @return 任务列表
     */
    public List<Task> findAll(String userId){
        return taskRepository.findAllTaskByUserId(userId);
    }

    /**
     * 查找用户的待办事项
     * @param userId 用户id
     * @return 待办事项列表
     */
    public List<Task> findTodo(String userId, String status) {
        return taskRepository.findAllTaskByUserIdAndStatus(userId, status);
    }
}
