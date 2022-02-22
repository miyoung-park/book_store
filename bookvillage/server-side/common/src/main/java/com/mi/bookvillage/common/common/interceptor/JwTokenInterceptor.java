package com.mi.bookvillage.common.common.interceptor;

import com.mi.bookvillage.common.common.annotation.NoJwtAuthorization;
import com.mi.bookvillage.common.common.security.JWTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/**
 *  PreHandle : 컨트롤러에 동작하기 전에 동작
 */
@Component
@Slf4j
public class JwTokenInterceptor implements HandlerInterceptor {


    /**
     * token 만료여부 파악
     * @return
     * @throws Exception */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 토큰인증이 필요없다는 뜻의 해당 어노테이션이 있는 지 확인
        NoJwtAuthorization noJwtAuthorization = handlerMethod.getMethodAnnotation(NoJwtAuthorization.class);
        // 어노테이션이 없다면 = 인증 로직 이후로 true;
        if( noJwtAuthorization == null ){
            String token = request.getHeader("Authorization");
            JWTokenUtil.checkToken( token );
            log.info("::::::::::: token :::: " + token + " :::::::::::::::");

        }
        // 어노테이션이 있다면 = 인증로직 없어도 됨
        if( noJwtAuthorization != null ){
            log.info("::::::::::: method has NoJwtokenAnnotaion ::::::::::: ");
        }

        return true;
    }
}