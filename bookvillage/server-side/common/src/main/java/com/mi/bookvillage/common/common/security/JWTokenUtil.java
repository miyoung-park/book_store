package com.mi.bookvillage.common.common.security;


import com.mi.bookvillage.common.common.exceptions.customException.JwtAuthException;
import com.mi.bookvillage.common.common.eum.ErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.Map;

// TODO: Util 안에서 Token 의 객체를 확인하는 로직 넣기
// TODO: token 체크 하는 부분을 어노테이션으로 만들어보기 ( Interceptor 제외하고 필요한 경우에만 사용할 수 있도록 )
@Slf4j
@Component // Bean 등록
public class JWTokenUtil { // Jwt 버전 올리기

    private static String SECRET_KEY = "access_token"; // 너무 짧음 -->  Secret Key 가 똑같이 겹칠 확률이 있다. 길게 바꾸기

    private static long EXPIRE_MINUTE = 30 * 60 * 1000; // 30min



    /**
     * ACCESS_TOKEN 토큰 발급 클래스
     * @param userObj
     * @return
     */
    public static String createJwToken(Map<String, Object> userObj) {
        Date now = new Date();
        return Jwts.builder()
                    .setHeaderParam("type" , "JWT")
                    .setSubject("auth_token")
                    .setExpiration(new Date(now.getTime() + Long.valueOf( EXPIRE_MINUTE )))
                    .claim("userId", userObj.get("userId"))
                    .signWith(SignatureAlgorithm.HS256 , SECRET_KEY.getBytes())
                    .compact();
    }


    /**
     * 전달 받은 토큰의 유효값 화인
     */
    public static boolean checkToken(String jwt) throws IllegalAccessError, NoSuchFieldException {

        if( jwt == null ){
            throw new JwtAuthException(ErrorCode.TOKEN_NOT_EXIST);
        }
        try {
                Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
                return true;
        } catch(ExpiredJwtException e) { // 토큰 만료
            throw new JwtAuthException(ErrorCode.TOKEN_EXPIRED);
        } catch(Exception e) {  // 그외의 오류
            log.error("JwtToken Exception ::: " + e.getMessage());
        }


        return false;

     }



    /**
     * 토큰 해독
     * @param jwt
     * @return
     */
    public static Map<String, Object> getTokenInfo(String jwt){

        if( jwt == null ){
            throw new JwtAuthException(ErrorCode.TOKEN_NOT_EXIST);
        }

        Map<String,Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();
            claimMap = claims;
        } catch(ExpiredJwtException e) { // 토큰 만료
            log.error("JwtToken Exception ::: " + e.getMessage());
            throw new JwtAuthException(ErrorCode.TOKEN_EXPIRED);
        } catch(Exception e) {  // 그외의 오류
            log.error("JwtToken Exception ::: " + e.getMessage());
        }
        return claimMap;
    }




}
