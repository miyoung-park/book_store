package com.mi.bookvillage.common.common.response;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/***
 * Usage :
 *
 * APIResponseBuilder responseBuilder = APIResponseBuilder.success().setData( resultVo );
 * ResponseEntity<Map<String, Object>> responseEntity = responseBuilder.build();
 *
 * APIResponseBuilder responseBuilder = APIResponseBuilder.success().putValue( "seq", 1 ).putValue( "name", "SangYoo" );
 * ResponseEntity<Map<String, Object>> responseEntity = responseBuilder.build();
 *
 * APIResponseBuilder.fail(APIResponseBuilder.ErrorCode.INVALID_INPUT_PARAM, "Seq must be not null.");
 * ResponseEntity<Map<String, Object>> responseEntity = responseBuilder.build();
 *
 */
public class ApiResponseBuilder {

    Map<String, Object> responseBodyMap = null;
    Object responseBodyObject = null;

    public ApiResponseBuilder(){

    }

    public ApiResponse build(){
        Object response = null;
        if( responseBodyObject != null ){  // responseBodyObject 가 null 인 경우 새로 생성
            response = responseBodyObject;
        } else if ( responseBodyMap != null ){ // responseBodyMap 가 null 인 경우 새로 생성
            response = responseBodyMap;
        } else {
            response = new HashMap<>();
        }

        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put("header" , new HashMap<>() {{
            put("code", "20000");
        }});

        responseBody.put("data" , response == null ? "" : response);

        ApiResponse apiResponse = new ApiResponse( response );
            return apiResponse;
    }

    /**
     * @param data 객체 형태의 파라미터
     * @return
     */
    public ApiResponseBuilder setData( Object data ) {
        this.responseBodyObject = data;
        return this;
    }

    public ApiResponseBuilder putValue( String key , Object val ){
        if( responseBodyObject != null ){
            throw new RuntimeException("Already Data put");
        }
        if( responseBodyMap == null ){
            responseBodyMap = new HashMap<>();
        }
        responseBodyMap.put( key , val );
        return this;
    }



}
