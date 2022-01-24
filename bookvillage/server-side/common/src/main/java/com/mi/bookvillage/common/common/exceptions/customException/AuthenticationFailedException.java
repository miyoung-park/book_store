package com.mi.bookvillage.common.common.exceptions.customException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFailedException extends RuntimeException{

    public AuthenticationFailedException(String msg){
        super(msg);
    }
    public AuthenticationFailedException(){
        super();
    }

}
