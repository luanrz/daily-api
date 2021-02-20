package cn.luanrz.daily.api.moudle.operation.extend;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 操作记录扩展服务切面
 */
@Component
@Aspect
public class OperationExtendAspect {

    private final OperationExtendService operationExtendService;

    public OperationExtendAspect(OperationExtendService operationExtendService) {
        this.operationExtendService = operationExtendService;
    }

    /**
     * Pointcut of OperationService.push()
     */
    @Pointcut("execution( * cn.luanrz.daily.api.moudle.operation.OperationService.push(..))")
    public void cutPushOperations() { }

    @AfterReturning(pointcut = "cutPushOperations()", returning = "operations")
    public void doPushOperations(List<Operation> operations) {
        operationExtendService.syncTasks(operations);
    }

}
