package com.mi.bookvillage.common.common.exceptions.customException;


import lombok.Getter;

public class VAuthException extends  RuntimeException {
    /**
     * VAuthErrorCode 구분 : 610 - 토큰 없음, 620 - 토큰 만료, 630 - 토큰 무효
     */

    @Getter
    private String errorCode;

    public VAuthException(VAuthErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode.getErrorCode();
    }


    /**
     * 토큰 상태 코드
     */
    public enum VAuthErrorCode {
          TOKEN_NOT_EXIST("610", "토큰이 없습니다.")
        , TOKEN_EXPIRED("620", "토큰이 만료되었습니다.")
        , TOKEN_NOT_VALID("630", "토큰이 무효합니다.");

        @Getter
        private final String errorCode;
        @Getter
        private final String message;


        VAuthErrorCode(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

    }



}
