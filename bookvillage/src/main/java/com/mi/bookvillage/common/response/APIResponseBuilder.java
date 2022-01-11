package com.mi.bookvillage.common.response;

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
public class APIResponseBuilder {

    Map<String, Object> responseBodyMap = null;
    Object responseBodyObject = null;

    // 기본 생성자
    public APIResponseBuilder(){
    }

    public APIResponse build() {

        Object response = null;
        if( responseBodyObject != null ) {
            response = responseBodyObject;
        } else if( responseBodyMap != null) {
            response = responseBodyMap;
        } else {
            response = new HashMap<>();
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("header",  new HashMap<>() {{
            put("code", "20000");
        }});

        responseBody.put("data", response == null ? "": response);

        APIResponse apiResponse = new APIResponse( response );
        return apiResponse;

    }

    public APIResponseBuilder setData(Object data) {
        this.responseBodyObject = data;
        return this;
    }

    public APIResponseBuilder putValue(String key, Object value) {
        if( responseBodyObject != null ) {
            throw new RuntimeException("Already data put...");
        }
        if( responseBodyMap == null) {
            responseBodyMap = new HashMap<>();
        }
        responseBodyMap.put(key, value);
        return this;
    }

}
