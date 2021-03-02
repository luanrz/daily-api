package cn.luanrz.daily.api.moudle.operation;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.OperationErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.base.infrastructure.jpa.manager.OperationManager;
import cn.luanrz.daily.api.moudle.operation.common.OperationContentParser;
import cn.luanrz.daily.api.moudle.operation.common.OperationTypeEnum;
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
    public List<Operation> find(String type, String currentOperationId, String userId) {
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
    public List<Operation> add(List<Operation> operations) {
        //校验操作记录
        checkOperations(operations);
        //增加操作记录
        return addOperations(operations);
    }

    @Override
    public Operation add(Operation operation) {
        return operationManager.save(operation);
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
        checkOperationsContent(operations);
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
    private void checkOperationsContent(List<Operation> operations) {
        operations.forEach(operation -> {
            if (!checkOperationContent(operation)){
                throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_CONTENT);
            }
        });
    }

    /**
     * 检查操作记录内容
     * @param operation 操作记录
     * @return 是否合法
     */
    private boolean checkOperationContent(Operation operation) {
        String content = operation.getContent();
        if (null == content){
            return false;
        }
        Task task = OperationContentParser.toObject(content);
        if (task.getTaskId() == null){
            return false;
        }
        OperationTypeEnum operationTypeEnum = OperationTypeEnum.valueOf(operation.getType());
        switch (operationTypeEnum){
            case ADD_TASK:
                return task.getContent() != null && task.getCreateTime() != null;
            case UPDATE_TASK:
                return task.getContent() != null;
        }
        return true;
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
