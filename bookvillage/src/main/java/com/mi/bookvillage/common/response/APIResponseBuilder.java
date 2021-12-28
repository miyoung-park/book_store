package com.mi.bookvillage.common.response;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class APIResponseBuilder {

    @Setter
    @Getter
    FilterProvider filterProvider;

    Map<String, Object> responseBodyMap = null;
    Object responseBodyObject = null;

    public APIResponseBuilder(){

    }
    // 생성자 주입방식
    public APIResponseBuilder( FilterProvider filterProvider ){
        this.filterProvider = filterProvider;
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

        APIResponse apiResponse = new APIResponse( response , filterProvider);
        return apiResponse;

    }

    public APIResponseBuilder setData(Object data) {
        this.responseBodyObject = data;
        return this;
    }

    public APIResponseBuilder putValue(String key, Object value) {
        if( responseBodyObject != null ) {
            throw new RuntimeException("Already data put.");
        }

        if( responseBodyMap == null) {
            responseBodyMap = new HashMap<>();
        }
        responseBodyMap.put(key, value);
        return this;
    }

}
