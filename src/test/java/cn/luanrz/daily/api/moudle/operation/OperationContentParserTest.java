package cn.luanrz.daily.api.moudle.operation;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.operation.common.OperationContentParser;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OperationContentParserTest {

    @Test
    void toJson() {
        String json = "{\"task_id\": \"123123\", \"content\": \"做作业\", \"create_time\": \"2021-02-01 11:29:21\"}";
        Task task = OperationContentParser.toObject(json);
        assertNotNull(task);
    }

    @Test
    void main() {
        Task task = new Task().setTaskId("123123").setContent("做作业").setCreateTime(new Date());
        String json = OperationContentParser.toJson(task);
        assertNotNull(json);
    }
}