package com.mi.bookvillage.common.common.exceptions.customException;

public class InvalidPasswordException extends RuntimeException{

    // TODO: InvalidPasswordException인데 ErrorCode.INVALID_USER 로 따로 보내 줄 필요가 있나. => 이미 명시된 Exception 이니 그냥 던져주기
    public InvalidPasswordException(){

    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
