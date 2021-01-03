package cn.luanrz.daily.api.moudle.user;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
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
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Login（登录）")
    @ApiOperationSupport(order = 1, ignoreParameters = {"userId"})
    @PostMapping("/login")
    public UserAuth login(UserAuth userAuth) {
        return userService.loginByUsername(userAuth);
    }

    @ApiOperation(value = "Register（注册）")
    @ApiOperationSupport(order = 2, ignoreParameters = {"userId"})
    @PostMapping("/register")
    public UserAuth register(UserAuth userAuth){
        return userService.register(userAuth);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "Wechat login（微信登陆）")
    @PostMapping("/login/wechat")
    public UserAuth loginByWechat(String wechatCode) {
        return userService.loginByWechatCode(wechatCode);
    }

}
