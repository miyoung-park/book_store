package com.mi.bookvillage.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenAuthInterceptor implements HandlerInterceptor {

    /**
     * token 만료여부 파악 / return 해당 userInfo
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        log.info("requestURL ::: " + requestUrl);
        //로그인 경로 제외
        if(requestUrl.contains("/login")){
            return true;
        }



        String token = request.getHeader("Authorization");

        return true;
    }
}
