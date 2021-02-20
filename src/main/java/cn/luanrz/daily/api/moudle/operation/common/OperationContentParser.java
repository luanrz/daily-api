package cn.luanrz.daily.api.moudle.operation.common;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.OperationErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 操作记录内容解析器
 */
public class OperationContentParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Task toObject(String json){
        try {
            return mapper.readValue(json, Task.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_CONTENT);
        }
    }

    public static String toJson(Task task){
        try {
            return mapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw DailyException.getInstance(OperationErrorEnum.INCORRECT_OPERATION_CONTENT);
        }
    }

}
