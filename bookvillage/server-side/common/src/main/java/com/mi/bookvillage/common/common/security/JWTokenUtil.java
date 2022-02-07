package com.mi.bookvillage.common.common.security;


import com.mi.bookvillage.common.common.annotation.JwtAuthorization;
import com.mi.bookvillage.common.common.exceptions.customException.VAuthException;
import io.jsonwebtoken.*;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

// TODO: Util 안에서 Token 의 객체를 확인하는 로직 넣기 (완)
// TODO: token 체크 하는 부분을 어노테이션으로 만들어보기 ( Interceptor 제외하고 필요한 경우에만 사용할 수 있도록 )
@Slf4j
@Aspect
@Component // Bean 등록
public class JWTokenUtil {

    private static final String SECRET_KEY = "AccessTokenForAuthentication";
    private static final long EXPIRE_MINUTE = 1 * 60 * 1000; // 30min

    /**
     * ACCESS_TOKEN 토큰 발급 클래스
     */
    public static String createJwToken(Map<String, Object> userObj) {
        Date now = new Date();
        return Jwts.builder()
                    .setHeaderParam("type" , "JWT")
                    .setSubject("auth_token")
                    .setExpiration(new Date(now.getTime() +  EXPIRE_MINUTE ))
                    .claim("userId", userObj.get("userId"))
                    .signWith(SignatureAlgorithm.HS256 , SECRET_KEY.getBytes())
                    .compact();
    }


    /**
     * 전달 받은 토큰의 유효값 화인 ( @JwtAuthorization 사용 )
     *
     */
    public static void checkToken( String jwt) {

        if( jwt == null ){
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_NOT_EXIST);
        }
        try {
                Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody();

        } catch(ExpiredJwtException e) { // 토큰 만료
            log.error("JwtToken Exception ::: " + e.getMessage());
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_EXPIRED);
        } catch(Exception e) {  // 그외의 오류
            log.error("JwtToken Exception ::: " + e.getMessage());
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_NOT_VALID);
        }
    }



    /**
     * 토큰 해독
     */
    public static Map<String, Object> getTokenInfo(String jwt){

        if( jwt == null ){
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_NOT_EXIST);
        }
        Map<String,Object> claimMap = null;

        try {
            claimMap = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .parseClaimsJws(jwt)
                        .getBody();
        } catch(ExpiredJwtException e) { // 토큰 만료
            log.error("JwtToken Exception ::: " + e.getMessage());
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_EXPIRED);
        } catch(Exception e) {  // 그외의 오류
            log.error("JwtToken Exception ::: " + e.getMessage());
            throw new VAuthException(VAuthException.VAuthErrorCode.TOKEN_NOT_VALID);
        }
        return claimMap;
    }

    /**
     * 유저 아이디 정보 가져오기
     */
    public static String getUserIdFromToken( HttpServletRequest request ) {

        // Header 에서 토큰 GET
        String token = request.getHeader("Authorization");
        // Token 해독
        Map<String, Object> tokenInfo = getTokenInfo(token);

        return (String)tokenInfo.get("userId");

    }




}
