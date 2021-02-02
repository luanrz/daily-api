package cn.luanrz.daily.api.moudle.user.token;

import cn.luanrz.daily.api.base.exception.DailyException;
import cn.luanrz.daily.api.base.exception.error.GlobalErrorEnum;
import io.jsonwebtoken.MalformedJwtException;

/**
 * Jwt用户ID解析器
 */
public final class JwtUserIdParser {

    private JwtUserIdParser(){ }

    /**
     * 验证Token合法性并从从JWS字符串中获取用户ID
     * @return 用户ID
     */
    public static String getUserId(String jwt){
        String userId;
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
