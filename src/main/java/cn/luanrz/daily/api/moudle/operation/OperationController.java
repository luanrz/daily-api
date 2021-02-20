package cn.luanrz.daily.api.moudle.operation;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.Operation;
import cn.luanrz.daily.api.moudle.user.token.JwtUserIdParser;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Operations（操作记录）")
@ApiSupport(order = 3)
@CrossOrigin
@RestController
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;
    private final HttpServletRequest request;

    public OperationController(OperationService operationService, HttpServletRequest request) {
        this.operationService = operationService;
        this.request = request;
    }

    /**
     * 查询操作记录
     * @param type 查询类型
     * @param currentOperationId 当前操作记录id(仅type为latest时有效)
     * @return 操作记录列表
     */
    @ApiOperation(value = "拉取操作记录（Pull operations）")
    @ApiOperationSupport(order = 1)
    @GetMapping()
    public List<Operation> get(String type, String currentOperationId) {
        String userId = JwtUserIdParser.getUserId(request.getHeader("jwt"));
        return operationService.pull(type, currentOperationId, userId);
    }

    /**
     * 增加操作记录
     * @param operations 待入库的操作记录列表
     * @return 完整的操作记录列表
     */
    @ApiOperation(value = "推送操作记录（Push operations）")
    @ApiOperationSupport(order = 2)
    @PostMapping
    public List<Operation> post(@RequestBody List<Operation> operations) {
        String userId = JwtUserIdParser.getUserId(request.getHeader("jwt"));
        operations.forEach(operation -> operation.setUserId(userId));
        return operationService.push(operations);
    }
}
