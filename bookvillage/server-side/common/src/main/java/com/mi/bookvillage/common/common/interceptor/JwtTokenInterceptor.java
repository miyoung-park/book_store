package com.mi.bookvillage.common.common.interceptor;

import com.mi.bookvillage.common.common.security.JWTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  PreHandle : 컨트롤러에 동작하기 전에 동작
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {


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
        // Controller 에 없는 URI 가 인식되는 경우
        String token = request.getHeader("Authorization");

        // WebMvcConfig 에서 Exclude 된 URL 제외하고는 token 없거나 만료된 경우 Controller 접근 불가
         if( !JWTokenUtil.checkToken( token ) ) {
            return false;
        }
        return true;
    }
}
