package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;

import java.util.List;

/**
 * 任务服务
 */
public interface TaskService {
    /**
     * 查找用户的任务
     * @param type 查询类型
     * @param userId 用户id
     * @return 任务列表
     */
    List<Task> find(String type, String userId);

    /**
     * 增加任务,并在切面中同步更新操作记录
     * @param task 任务对象
     * @return 完整的任务对象
     */
    Task add(Task task);

    /**
     * 更新任务,并在切面中同步更新操作记录
     * @param task 任务对象
     * @return 完整的任务对象
     */
    Task update(Task task);

    /**
     * 删除任务,并在切面中同步更新操作记录
     * @param task 任务对象
     */
    void delete(Task task);
}
