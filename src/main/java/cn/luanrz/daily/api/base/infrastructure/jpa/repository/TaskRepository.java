package cn.luanrz.daily.api.base.infrastructure.jpa.repository;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务Repository
 */
@Repository
public interface TaskRepository extends CrudRepository<Task,String> {

    /**
     * 根据用户id查询所有任务
     * @param userId 用户id
     * @return 任务列表
     */
    List<Task> findAllTaskByUserId(String userId);

    /**
     * 根据用户id与任务状态查询所有任务
     * @param userId 用户id
     * @param status 任务状态
     * @return 任务列表
     */
    List<Task> findAllTaskByUserIdAndStatus(String userId, String status);
}
