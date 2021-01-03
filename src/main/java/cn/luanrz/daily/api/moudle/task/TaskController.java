package cn.luanrz.daily.api.moudle.task;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.GlobalErrorEnum;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Task;
import cn.luanrz.daily.api.moudle.user.token.JwtHelper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Task（任务）")
@ApiSupport(order = 2)
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private HttpServletRequest request;

    public TaskController(TaskService taskService, HttpServletRequest request) {
        this.taskService = taskService;
        this.request = request;
    }

    /**
     * 查找用户的所有任务
     * @return 任务列表
     */
    @ApiOperation(value = "Find all tasks（查询所有任务）")
    @ApiOperationSupport(order = 1)
    @GetMapping("/all")
    public List<Task> findAll() {
        return taskService.findAll(getUserId());
    }

    /**
     * 查找用户的待办事项
     * @return 待办事项列表
     */
    @ApiOperation(value = "Find todo tasks（查询待办事项）")
    @ApiOperationSupport(order = 2)
    @GetMapping("/todo")
    public List<Task> findTodo() {
        return taskService.findTodo(getUserId());
    }


    /**
     * 验证Token合法性并从从JWS字符串中获取用户ID
     * @return 用户ID
     */
    private String getUserId(){
        String userId;
        String jwt = request.getHeader("jwt");
        try {
            userId = JwtHelper.parseJws(jwt).get("userId").toString();
        }catch (IllegalArgumentException iae){
            throw DailyException.getInstance(GlobalErrorEnum.TOKEN_VERIFY_EMPTY);
        }catch ( MalformedJwtException mje){
            throw DailyException.getInstance(GlobalErrorEnum.TOKEN_VERIFY_FAIL);
        }
        return userId;
    }

}
