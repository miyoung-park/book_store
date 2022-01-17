package com.mi.bookvillage.common.common.eum;

import org.springframework.http.HttpStatus;

public enum ErrorCode{

    /**
     * 토큰 정보 없음
     */
    TOKEN_NOT_EXIST(HttpStatus.UNAUTHORIZED, "토큰이 없습니다."),

    /**
     * 토큰 만료
     */
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),

    /**
     * 패스워드 Error
     */
    INVALID_PASSWORD(HttpStatus.FORBIDDEN , "로그인 정보를 다시 확인해주세요.");


    private HttpStatus status;
    private String message;


    ErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}
