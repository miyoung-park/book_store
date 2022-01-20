package com.mi.bookvillage.common.common.exceptions.customException;

public class NotEnoughPointException extends RuntimeException {

    public NotEnoughPointException(String errorMessage){
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
