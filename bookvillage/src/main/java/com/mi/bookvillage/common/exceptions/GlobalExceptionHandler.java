package com.mi.bookvillage.common.exceptions;

import com.mi.bookvillage.common.exceptions.customException.InvalidPasswordException;
import com.mi.bookvillage.common.exceptions.customException.JwtAuthException;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.eum.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice // 모든 Exception 을 처리
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Password Error
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<Map<String, Object>> InvalidPasswordException(InvalidPasswordException e) {
        // 에러 로그 출력
        log.error("Invalid_User Exception ::: " + e.getMessage());
        return APIResponse.builder().error(e.getMessage()).build(); // TODO: APIResponse 에 Status 로 HttpStatus 값 세팅하는 방법을 찾아보기 + ErrorCode 와는 별도임을 기억  !
    }

    /**
     * Authentication Error
     * @param e
     * @return
     */
    @ExceptionHandler(JwtAuthException.class)
    protected ResponseEntity<Map<String, Object>> JwtAuthException(JwtAuthException e) {
        // 에러 로그 출력
        log.error("Authentication Exception ::: " + e.getMessage());
        return APIResponse.builder().error(e.getError()).build();
    }




    /**
     * Other exceptions
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception error, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // TODO: 명시해준 에러처리 외에 다른 에러들을 어떻게 처리하는 것인지에 대해서 좀 더 고민 할 필요가 있음 !
        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("errorCode" , "50000");
        errorObject.put("message" , error.getMessage());
        return super.handleExceptionInternal(error, errorObject, headers, status, request);
    }




}
