package com.mi.bookvillage.common.common.exceptions;


import com.mi.bookvillage.common.common.exceptions.customException.VAuthException;
import com.mi.bookvillage.common.common.response.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Order : 우선순위 지정할 때 사용( 가장 낮은 숫자가 우선순위가 높다 ) // 중복되는 상황이 생길 때 어디서 먼저 에러를 처리할 지 우선순위
 * @ExceptionHandler : 이 클래스에서 value 에 해당하는 Exception 을 잡아서 처리하겠다는 뜻
 *                   : 현재는 모든 Exception 이 해당 클래스로 들어오기 때문에 잡히는 모든 value 를 이 클래스에서 처리..
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {


    // 200 성공
    // 400 Bad Request 유효성 검증 실패

    // 공통부
    // 500 서버에러
    // 401 Unauthorized 인증실패 catch exception
    // 403 Forbidden 데이터 권한 없음 catch exception
    // 403 Forbidden 실행 권한 없음 catch exception

    /**
     * 403 For Bidden
     * 권한 부족( 토큰값 X , 토큰 만료 )
     * @return
     */
    @ExceptionHandler(value = {VAuthException.class})
    protected ResponseEntity<ApiErrorResponse> handleAuthenticationFailException(VAuthException e) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN) // 403
                .body( ApiErrorResponse.build( e.getErrorCode(), e.getMessage() ) );
    }



    /**
     * 400 Bad Request
     * 요청 유효성 검증 실패
     * @return
     */
    @ExceptionHandler(value = { ApiException.class })
    protected ResponseEntity<ApiErrorResponse> handleInvalidRequestException(ApiException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body( ApiErrorResponse.build( e.getErrorCode(), e.getMessage() ) );
    }



    /**
     * 기타 서버 에러
     * @return
     */
    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body( ApiErrorResponse.build( "50000", e.getMessage() ) );
    }


    /**
     * 캐치한 익셉션 이외의 500 서버 에러
     * @param e
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        return super.handleExceptionInternal(e, ApiErrorResponse.build( "50000", e.getMessage() ), headers, status, request);
    }



}
