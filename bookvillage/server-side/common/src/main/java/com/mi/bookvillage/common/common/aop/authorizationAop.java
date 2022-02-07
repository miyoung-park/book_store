package com.mi.bookvillage.common.common.aop;

import com.mi.bookvillage.common.common.security.JWTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class authorizationAop {

    /**
     * TODO: Q. AOP 를 이용해 메소드 실행 전에 해당 어노테이션 존재유무를 파악 후 메소드를 실행 혹은 Exception 을 발생하게 하는 방법이
     *          Interceptor 를 사용해 URI 구분으로 token 유무를 파악하는 방법과 어떤 차이나 장점이 있는지...
     *          Resolver 를 작접 구현해서 사용하려 했으나 메소드의 파라미터보다는 메소드 자체에 걸어주기 위해 AOP 사용했음
     *       Q. 원래 JWTokenUtil 에 AOP 를 구현했으나 한 눈에 볼 수 있게 패키지를 구분 지었는데 어떤 방법이 나은지 ?
     */
    @Before("@annotation(com.mi.bookvillage.common.common.annotation.JwtAuthorization)")
    public void tokenAuthorizationAop(){

        ServletRequestAttributes requestAttributes = ( ServletRequestAttributes ) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("Authorization");

        JWTokenUtil.checkToken( token );
    }
}
