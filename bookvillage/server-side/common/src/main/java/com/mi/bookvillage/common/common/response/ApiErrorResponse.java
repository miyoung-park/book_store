package com.mi.bookvillage.common.common.response;

import lombok.Getter;

import java.util.Map;

public class ApiErrorResponse {

    @Getter
    private String errorCode;
    @Getter
    private String errorMessage;
    @Getter
    private Map<String, Object> tag; // 추가 정보 입력

    public static ApiErrorResponse build(String code, String message){
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.errorCode = code;
        errorResponse.errorMessage = message;

        return errorResponse;
    }

}
