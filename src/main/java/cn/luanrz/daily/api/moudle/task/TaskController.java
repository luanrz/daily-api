package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.user.token.JwtUserIdParser;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Tasks（任务）")
@ApiSupport(order = 2)
@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final HttpServletRequest request;

    public TaskController(TaskService taskService, HttpServletRequest request) {
        this.taskService = taskService;
        this.request = request;
    }

    /**
     * 查找任务
     * @param type 查询类型
     * @return 任务列表
     */
    @ApiOperation(value = "查询任务（Find tasks）")
    @ApiOperationSupport(order = 1)
    @GetMapping
    public List<Task> get(String type) {
        String userId = JwtUserIdParser.getUserId(request.getHeader("jwt"));
        return taskService.find(type, userId);
    }

    /**
     * 增加任务
     * @param task 任务:包含"任务内容"
     * @return 完整的任务
     */
    @ApiOperation(value = "增加任务（Add task）")
    @ApiOperationSupport(order = 2)
    @PostMapping
    public Task post(@RequestBody Task task){
        task.setUserId(JwtUserIdParser.getUserId(request.getHeader("jwt")));
        return taskService.add(task);
    }

    /**
     * 更新任务
     * @param task 任务:包含"任务id"与"待修改的任务属性"(任务内容,状态,完成时间)
     * @return 完整的任务
     */
    @ApiOperation(value = "更新任务（Update task）")
    @ApiOperationSupport(order = 2)
    @PatchMapping
    public Task patch(@RequestBody Task task){
        task.setUserId(JwtUserIdParser.getUserId(request.getHeader("jwt")));
        return taskService.update(task);
    }

    /**
     * 删除任务
     * @param task 任务:包含"任务id"
     */
    @ApiOperation(value = "删除任务（DELETE task）")
    @ApiOperationSupport(order = 3)
    @DeleteMapping
    public void delete(@RequestBody Task task){
        task.setUserId(JwtUserIdParser.getUserId(request.getHeader("jwt")));
        taskService.delete(task);
    }

}
