package com.mi.bookvillage.common.common.exceptions;

import lombok.Getter;

public enum ApiServiceErrorCode {

    /**
     * 회원정보 없음
     */
    INVALID_USER("U10001"),

    /**
     * 잘못된 비밀번호
     */
    INVALID_PASSWORD("U10002"),

    /**
     * 포인트 부족
     */
    NOT_ENOUGH_POINT("P50001"),

    /**
     * 데이터 정보 없음
     */
    DATA_NOT_FOUND("E40001"),


    ;
    @Getter
    private final String code;

    ApiServiceErrorCode( String code ){
        this.code = code;
    }
}
