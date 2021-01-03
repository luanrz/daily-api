package cn.luanrz.daily.api.moudle.user.token;

import cn.luanrz.daily.api.base.infrastructure.jpa.entiy.UserAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

/**
 * Jwt帮助类
 */
public class JwtHelper {

    private static SecretKey key = Keys.hmacShaKeyFor( "123456789123456789123456789123456789".getBytes());

    /**
     * 创建JWS(JSON Web Signature)
     *
     * @param userAuth 用户信息，将用户id存入claims
     * @return JWS格式的token字符串
     */
    public static String createJws(UserAuth userAuth) {
        return Jwts.builder().claim("userId", userAuth.getUserId()).signWith(key).compact();
    }

    /**
     * 解析JWS
     *
     * @param jwsString JWS格式的token字符串
     * @return JWS body
     * @throws JwtException Jwt异常
     */
    public static Claims parseJws(String jwsString) throws JwtException {
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsString);
        return jws.getBody();
    }
}
