package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.common.IdGenerator;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.TaskManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 任务服务实现
 */
@Service
public class TaskServiceImpl implements TaskService {

    /** 任务Manager */
    private final TaskManager taskManager;

    public TaskServiceImpl(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public List<Task> find(String type, String userId) {
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

    @Override
    public Task add(Task task) {
        if (null == task.getTaskId()){
            task.setTaskId(IdGenerator.generateUUID());
        }
        task.setCreateTime(new Date());
        task.setStatus("0");
        return taskManager.add(task);
    }

    @Override
    public  Task update(Task task) {
        return taskManager.update(task);
    }


    @Override
    public void delete(Task task) {
        taskManager.delete(task);
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

}
