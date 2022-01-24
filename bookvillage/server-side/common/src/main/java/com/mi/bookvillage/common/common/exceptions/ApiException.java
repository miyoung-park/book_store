package com.mi.bookvillage.common.common.exceptions;

import lombok.Getter;

public class ApiException extends RuntimeException {

    @Getter
    private String errorCode;

    public ApiException(String errorCode){
        super();
        this.errorCode = errorCode;
    }

    public ApiException( ApiServiceErrorCode errorCode, String message ){
        super( message );
        this.errorCode = errorCode.getCode();
    }

}
