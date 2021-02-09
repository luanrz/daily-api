package cn.luanrz.daily.api.base.infrastructure.jpa.manager;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.OperationErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.base.infrastructure.jpa.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 操作记录Manager
 */
@Service
public class OperationManager {
    /** 操作记录Repository */
    private final OperationRepository repository;

    public OperationManager(OperationRepository repository) {
        this.repository = repository;
    }

    /**
     * 查找所有的操作记录
     * @param userId 用户ID
     * @return 操作记录列表
     */
    public List<Operation> findAll(String userId){
        return repository.findAllByUserId(userId);
    }

    /**
     * 查找最新的操作记录
     * @param userId 用户ID
     * @param currentOperationId 当前操作记录id
     * @return 最新的操作记录
     */
    public List<Operation> findLatest(String userId, String currentOperationId){
        Operation operation = repository.findByUserIdAndOperationId(userId, currentOperationId);
        if (null == operation){
            throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_ID);
        }
        // 查询当前操作记录之后的所有操作记录
        return repository.findAllByUserIdAndOperateTimeAfter(userId, operation.getOperateTime());
    }

    /**
     * 增加操作记录
     * @param operations 操作记录列表
     * @return 操作记录列表
     */
    public List<Operation> saveAll(List<Operation> operations){
        List<Operation> newOperations = new ArrayList<>();
        Iterable<Operation> operationIterable = repository.saveAll(operations);
        for (Operation operation : operationIterable) {
            newOperations.add(operation);
        }
        return newOperations;
    }
}
