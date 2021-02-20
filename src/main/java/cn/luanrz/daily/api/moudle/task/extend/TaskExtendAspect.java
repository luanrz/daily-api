package cn.luanrz.daily.api.moudle.task.extend;


import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.OperationController;
import cn.luanrz.daily.api.moudle.operation.common.OperationTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 任务扩展服务切面
 */
@Component
@Aspect
public class TaskExtendAspect {

    private final TaskExtendService taskExtendService;

    public TaskExtendAspect(TaskExtendService taskExtendService) {
        this.taskExtendService = taskExtendService;
    }

    /**
     * Pointcut of TaskService.add()
     */
    @Pointcut("execution( * cn.luanrz.daily.api.moudle.task.TaskService.add(..))")
    public void cutAddTask() { }

    /**
     * Pointcut of TaskService.update()
     */
    @Pointcut("execution( * cn.luanrz.daily.api.moudle.task.TaskService.update(..))")
    public void cutUpdateTask() { }

    /**
     * Pointcut of TaskService.delete()
     */
    @Pointcut("execution( * cn.luanrz.daily.api.moudle.task.TaskService.delete(..))")
    public void cutDeleteTask() { }


    @AfterReturning(pointcut = "cutAddTask()", returning = "task")
    public void doAddTask(Task task){
        if(isOperationsScene()){
            return;
        }
        taskExtendService.syncOperation(OperationTypeEnum.ADD_TASK, task);
    }

    @AfterReturning(pointcut = "cutUpdateTask()", returning = "task")
    public void doUpdateTask(Task task){
        if(isOperationsScene()) {
            return;
        }
        taskExtendService.syncOperation(OperationTypeEnum.UPDATE_TASK, task);
    }

    @Around("cutDeleteTask()")
    public Object doDeleteTask(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(isOperationsScene()){
            return proceedingJoinPoint.proceed();
        }
        Task task = (Task) proceedingJoinPoint.getArgs()[0];
        taskExtendService.syncOperation(OperationTypeEnum.DELETE_TASK, task);
        return proceedingJoinPoint.proceed();
    }

    /**
     * 是否为"push操作记录"场景
     *
     * 背景:
     * Task与Operation存在联动关系,即:增加一个Task时,也会同步增加一个Operation,反之亦然. 具体实现方式如下:
     * 1. 增加Task时同步Operation: TaskExtendAspect切面监听TaskService的add(),update(),delete(), 并调用OperationService的add()
     * 2. 增加Operation时同步Task: OperationExtendAspect切面监听OperationService的push(), 并调用TaskService的add(),update(),delete()
     *
     * 在上述2中调用"TaskService的add(),update(),delete()", 此时重复触发了"1", 通过判断是否为"push操作记录"场景以避免该副作用
     */
    private boolean isOperationsScene() {
        String className = OperationController.class.getName();
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .anyMatch(stackTraceElement -> className.equals(stackTraceElement.getClassName()));
    }

}
