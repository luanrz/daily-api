package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.TaskErrorEnum;
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
    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    /**
     * 查找用户的所有任务
     * @param userId 用户id
     * @return 任务列表
     */
    public List<Task> findAll(String userId){
        return taskRepository.findAllByUserId(userId);
    }

    /**
     * 查找用户的待办事项
     * @param userId 用户id
     * @return 待办事项列表
     */
    public List<Task> findTodo(String userId) {
        return taskRepository.findAllByUserIdAndStatus(userId, "0");
    }

    /**
     * 根据用户id与任务id查询一个任务
     * @param userId 用户id
     * @param taskId 任务id
     * @return 完整的任务对象
     */
    public Task findOne(String userId, String taskId){
        return taskRepository.findByUserIdAndTaskId(userId, taskId);
    }

    /**
     * 增加任务
     * @param task 任务对象
     * @return 完整的任务对象
     */
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    /**
     * 更新任务
     * 只允许修改content,status及DeadlineTime
     * @param task 任务对象
     * @return 完整的任务对象
     */
    public Task update(Task task) {
        Task oldTask = findOne(task.getUserId(), task.getTaskId());
        if (oldTask == null){
            throw DailyException.getInstance(TaskErrorEnum.INCORRECT_TASK_ID);
        }
        if (task.getContent() == null){
            task.setContent(oldTask.getContent());
        }
        if (task.getStatus() == null){
            task.setStatus(oldTask.getStatus());
        }
        if (task.getDeadlineTime() == null){
            task.setDeadlineTime(oldTask.getDeadlineTime());
        }
        task.setCreateTime(oldTask.getCreateTime());
        task.setUserId(oldTask.getUserId());
        return taskRepository.save(task);
    }

    /**
     * 删除任务
     * @param task 任务对象
     */
    public void delete(Task task) {
        Task oldTask = findOne(task.getUserId(), task.getTaskId());
        if (oldTask == null){
            throw DailyException.getInstance(TaskErrorEnum.INCORRECT_TASK_ID);
        }
        taskRepository.deleteById(task.getTaskId());
    }
}
