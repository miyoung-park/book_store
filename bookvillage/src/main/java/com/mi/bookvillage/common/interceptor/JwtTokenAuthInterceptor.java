package com.mi.bookvillage.common.interceptor;

import com.mi.bookvillage.common.security.JWTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  PreHandle : 컨트롤러에 동작하기 전에 동작
 */
@Component
@Slf4j
public class JwtTokenAuthInterceptor implements HandlerInterceptor {


    /**
     * token 만료여부 파악
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Controller 에 없는 URI 가 인식되는 경우 ㄷ
        log.info("Request URI ::: " + request.getRequestURI());
        String token = request.getHeader("Authorization");

        // WebMvcConfig 에서 Exclude 된 URL 제외하고는 token 없거나 만료된 경우 Controller 접근 불가
         if( !JWTokenUtil.checkToken(token) ) {
            // JWTokenUtil.checkToken(token) 에서 Exception + log 처리 !
            return false;
        }
        return true;
    }
}
