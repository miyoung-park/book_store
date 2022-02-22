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
     * 인증로직 필요 - 불필요 구분
     * 필요시 token 인증 구현
     * @return
     * @throws Exception */
    // TODO: 토큰 인증을 어노테이션으로 진행하는 로직 ( + 인터셉터와의 결합 ) (완)
    //       컨트롤러에 도달해서 동작하기 전에 prehandle 이 시행되는데, 해당 메소드가 'no인증' 어노테이션을 가지고 있는지 없는지 판단한다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
        * HandlerMethod
        * : @RequestMapping 과 하위 매핑 어노테이션이 붙은 메소드 정보를 추상화 한 객체로
        *   디스패처 서블릿은 어플리케이션이 실행될 시점에 모든 컨트롤러의 빈을 살피고, 매핑 후보가 되는 메소들을 HandlerMethod 에 저장해둔다.
        *   이 후 실제 요청이 들어오면 저장 둔 메소드 목록중에서 조건에 적합한 HandlerMethod 를 참조하여 매핑되는 메소드를 실행한다.
        *   HandlerMethod 자체가 어떤 로직을 실행하는 것이 아닌 메소드들의 참조 정보를 가지고 있다고 보면 된다.
        *
        * HandlerMethod 가 가지고 있는 정보
        * : 빈 객체 / 메소드 메타정보 / 메소드 파라미터 정보 / 메소드 어노테이션 메타정보 / 메소드 리턴값 메타정보
        */
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 토큰인증이 필요없다는 뜻의 해당 어노테이션이 있는 지 확인
        NoJwtAuthorization noJwtAuthorization = handlerMethod.getMethodAnnotation(NoJwtAuthorization.class);
        // 어노테이션이 없다면 = 인증 로직 이후로 true;
        if( noJwtAuthorization == null ){
            String token = request.getHeader("Authorization");
            JWTokenUtil.checkToken( token );
        }
        // 어노테이션이 있다면 = 인증로직 없어도 됨
        if( noJwtAuthorization != null ){
            log.info("::::::::::: method has NoJwtokenAnnotaion ::::::::::: ");
        }

        return true;
    }
}