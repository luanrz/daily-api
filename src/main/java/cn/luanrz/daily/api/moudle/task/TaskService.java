package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.common.IdGenerator;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.TaskManager;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 查找用户的任务
     * @param type 查询类型
     * @param userId 用户id
     * @return 任务列表
     */
    List<Task> find(String type, String userId) {
        final String typeAll = "all";
        final String typeTodo = "todo";
        if (typeAll.equalsIgnoreCase(type)){
            return findAll(userId);
        }
        if (typeTodo.equalsIgnoreCase(type)){
            return findTodo(userId);
        }
        //不指定type时默认查询所有任务
        return findAll(userId);
    }

    /**
     * 查找用户的所有任务
     * @param userId 用户id
     * @return 任务列表
     */
    private List<Task> findAll(String userId){
        return taskManager.findAll(userId);
    }

    /**
     * 查找用户的待办事项
     * @param userId 用户id
     * @return 待办事项列表
     */
    private List<Task> findTodo(String userId) {
        return taskManager.findTodo(userId);
    }

    /**
     * 增加任务
     * @param task 任务对象
     * @return 完整的任务对象
     */
    public Task add(Task task) {
        task.setTaskId(IdGenerator.generateUUID());
        task.setCreateTime(new Date());
        task.setStatus("0");
        return taskManager.add(task);
    }

    /**
     * 更新任务
     * @param task 任务对象
     * @return 完整的任务对象
     */
    public  Task update(Task task) {
        return taskManager.update(task);
    }

    /**
     * 删除任务
     * @param task 任务对象
     */
    public void delete(Task task) {
        taskManager.delete(task);
    }

}
