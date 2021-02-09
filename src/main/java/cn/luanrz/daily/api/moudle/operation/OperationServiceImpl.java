package cn.luanrz.daily.api.moudle.operation;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.OperationErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.OperationManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 操作记录服务实现
 */
@Service
public class OperationServiceImpl implements OperationService{
    /** 操作记录Manager */
    private final OperationManager operationManager;

    public OperationServiceImpl(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    @Override
    public List<Operation> pull(String type, String currentOperationId, String userId) {
        final String typeAll = "all";
        final String typeTodo = "latest";
        if (typeAll.equalsIgnoreCase(type)){
            return findAll(userId);
        }
        if (typeTodo.equalsIgnoreCase(type)){
            return findLatest(userId, currentOperationId);
        }
        //不指定type时默认查询所有操作记录
        return findAll(userId);
    }

    @Override
    public List<Operation> push(List<Operation> operations, String userId) {
        operations.forEach(operation -> operation.setUserId(userId));
        //校验操作记录
        checkOperations(operations);
        //增加操作记录
        return addOperations(operations);
    }

    /**
     * 查询所有的操作记录
     * @param userId 用户ID
     * @return 操作记录列表
     */
    private List<Operation> findAll(String userId) {
        return operationManager.findAll(userId);
    }

    /**
     * 查询最新的操作记录
     * @param userId 用户ID
     * @param currentOperationId 当前操作记录ID
     * @return 操作记录列表
     */
    private List<Operation> findLatest(String userId, String currentOperationId) {
        return operationManager.findLatest(userId, currentOperationId);
    }

    /**
     * 校验操作记录
     * @param operations 操作记录列表
     */
    private void checkOperations(List<Operation> operations) {
        //校验操作记录类型
        checkOperationType(operations);
        //检查操作记录内容
        checkOperationContent(operations);
    }

    /**
     * 校验操作记录类型
     * @param operations 操作记录列表
     */
    private void checkOperationType(List<Operation> operations) {
        operations.forEach(operation -> {
            boolean isOperationTypeCorrect = Arrays.stream(OperationTypeEnum.values())
                    .anyMatch(operationTypeEnum -> Objects.equals(operationTypeEnum.name(), operation.getType()));
            if (!isOperationTypeCorrect){
                throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_TYPE);
            }
        });
    }

    /**
     * 检查操作记录内容
     * @param operations 操作记录列表
     */
    private void checkOperationContent(List<Operation> operations) {
        operations.forEach(operation -> {
            boolean isOperationContentCorrect = true;
            String content = operation.getContent();
            if (null == content){
                isOperationContentCorrect = false;
            }
            // todo check content properties
            if (!isOperationContentCorrect){
                throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_CONTENT);
            }
        });
    }

    /**
     * 增加操作记录
     * @param operations 操作记录列表
     * @return 操作记录列表
     */
    private List<Operation> addOperations(List<Operation> operations) {
        return operationManager.saveAll(operations);
    }

}
