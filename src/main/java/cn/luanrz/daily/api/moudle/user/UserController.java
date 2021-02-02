package cn.luanrz.daily.api.moudle.user;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserDetail;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User（用户）")
@ApiSupport(order = 1)
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登录（Login）")
    @ApiOperationSupport(order = 1, ignoreParameters = {"userId"})
    @PostMapping("/login")
    public UserDetail login(@RequestBody UserAuth userAuth) {
        return userService.loginByUsername(userAuth);
    }

    @ApiOperation(value = "注册（Register）")
    @ApiOperationSupport(order = 2, ignoreParameters = {"userId"})
    @PostMapping("/register")
    public UserDetail register(@RequestBody UserAuth userAuth){
        return userService.register(userAuth);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "微信登陆（Wechat login）")
    @PostMapping("/login/wechat")
    public UserDetail loginByWechat(String wechatCode) {
        return userService.loginByWechatCode(wechatCode);
    }

}
