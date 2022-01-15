package com.mi.bookvillage.common.exceptions.customException;

import com.mi.bookvillage.common.eum.ErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthException extends RuntimeException{

    ErrorCode errorCode;

   public JwtAuthException( ErrorCode errorCode ){
       this.errorCode = errorCode;
   }

   public ErrorCode getError(){
       return this.errorCode;
   }


}
