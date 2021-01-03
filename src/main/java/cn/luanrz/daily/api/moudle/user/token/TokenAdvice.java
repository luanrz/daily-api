package cn.luanrz.daily.api.moudle.user.token;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 登录之后，生成JWT格式的token字符串，并写入响应头
 */
@ControllerAdvice
public class TokenAdvice implements ResponseBodyAdvice<UserAuth> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //仅在登录操作时该advice有效
        String methodName = methodParameter.getMethod().getName();
        return "login".equals(methodName) ||"loginByWechat".equals(methodName);
    }

    @Override
    public UserAuth beforeBodyWrite(UserAuth userAuth, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String jwt = JwtHelper.createJws(userAuth);
        //将token放入Response头部
        serverHttpResponse.getHeaders().add("jwt", jwt);
        serverHttpResponse.getHeaders().add("Access-Control-Expose-Headers","jwt");
        return userAuth;
    }
}
