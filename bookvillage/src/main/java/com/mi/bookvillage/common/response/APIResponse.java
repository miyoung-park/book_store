package com.mi.bookvillage.common.response;


import com.mi.bookvillage.common.eum.ErrorCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
// TODO: Exception과 Status를 넘겨주는 방식 RESPONSE_API 추가
public class APIResponse {

    private Object data;
    private ErrorCode errorCode;
    private String errorMsg;
    private HttpStatus errorStatus;
    private Map<String, Object> responseBodyMap;


    public APIResponse(APIResponseBuilder builder){
        this.data = builder.data;
        this.errorCode = builder.errorCode;
        this.errorMsg = builder.errorMsg;
        this.errorStatus = builder.errorStatus;
        this.responseBodyMap = builder.responseBodyMap;
    }

    public static APIResponseBuilder builder(){
        return new APIResponseBuilder();
    }


    // 내부 클래스 생성
    public static class APIResponseBuilder {

        private Object data;
        private ErrorCode errorCode;
        private String errorMsg;
        private HttpStatus errorStatus;
        private Map<String, Object> responseBodyMap;


        public APIResponseBuilder error() {
            return this;
        }


        public APIResponseBuilder error(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }
        public APIResponseBuilder error(ErrorCode errorCode) {
            this.errorCode = errorCode;
            this.errorMsg = errorCode.getMessage();
            this.errorStatus = errorCode.getStatus();
            return this;
        }

        public APIResponseBuilder success(){
            return this;
        }

        public APIResponseBuilder success(Object value){
            this.data = value;
            return this;
        }

        public APIResponseBuilder success(String key , Object value){
            if( responseBodyMap == null) {
                responseBodyMap = new HashMap<>();
            }
            responseBodyMap.put( key ,  value);
            return this;
        }

        public ResponseEntity<Map<String, Object>> build(){
            if( this.errorStatus != null ){
                return new ResponseEntity( new APIResponse(this) , this.errorStatus ); // TODO: 해당되는 ErrorStatus 를 명시해주되, ErrorCode를 각기 다르게 해서 프론트에서 개별로 처리할 수 있도록 선택권을 준다.
                                                                                              // TODO : 이런 경우에 사용하게 되는게 ErrorCode enum ! 존재 이유에 대해서 생각해보기
            }
            if( this.errorMsg != null ) {
                return new ResponseEntity(new APIResponse(this) , HttpStatus.BAD_GATEWAY );
            }

            return new ResponseEntity(new APIResponse(this), HttpStatus.OK );
        }
    }


}
