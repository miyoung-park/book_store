package com.mi.bookvillage.common.common.response;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("apiResponseBuilderFactory")
@RequiredArgsConstructor
public class ApiResponseBuilderFactory {

    /*
     * 필터 적용은 일단 (-)
     * private final ApiJsonFilterService apiJsonFilterService;
     */

    public ApiResponseBuilder success( Object data ){
       ApiResponseBuilder instance = new ApiResponseBuilder();
        instance.setData(data);

        return instance;
    }

    public ApiResponseBuilder success(){
        ApiResponseBuilder instance = new ApiResponseBuilder();
        return instance;
    }

    public ApiResponseBuilder injectFilterProvider( ApiResponseBuilder responseBuilder ){
        return responseBuilder;
    }
}
