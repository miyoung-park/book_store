package com.mi.bookvillage.common.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component // Bean 등록
@Slf4j
public class JWTokenUtil {

    private static String SECRET_KEY = "access_token";
    private static long EXPIRE_MINUTE = 30 * 60 * 1000; // 30min

    /**
     * ACCESS_TOKEN 토큰 발급 클래스
     * @param userObj
     * @return
     */
    public static String createJwToken(Map<String, Object> userObj) {
        return Jwts.builder()
                    .setHeaderParam("type" , "JWT")
                    .setSubject("auth_token")
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MINUTE))
                    .claim("role", userObj.get("role"))
                    .claim("userId", userObj.get("userId"))
                    .signWith(SignatureAlgorithm.HS256 , SECRET_KEY.getBytes())
                    .compact();
    }


    /**
     * 전달 받은 토큰의 유효값 화인
     */
    public static boolean checkToken(final String jwt) throws IllegalAccessError {

        try {
                Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
                return true;
        } catch(ExpiredJwtException e) { // 토큰 만료
            log.error("expired token error ::: " + e.getMessage());
        } catch(Exception e) {  // 그외의 오류
            log.error("exception of token ::: " + e.getMessage());
        }
        return false;
     }


    /**
     * 토큰 해독
     * @param jwt
     * @return
     */
    public static Map<String, Object> getTokenInfo(String jwt){
        Map<String,Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();
            claimMap = claims;
        } catch(ExpiredJwtException e) { // 토큰 만료
            log.error("expired token error ::: " + e.getMessage());
        } catch(Exception e) {  // 그외의 오류
            log.error("exception of token ::: " + e.getMessage());
        }
        return claimMap;
    }



}
