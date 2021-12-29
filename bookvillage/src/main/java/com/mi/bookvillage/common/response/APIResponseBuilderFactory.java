package com.mi.bookvillage.common.response;

import org.springframework.stereotype.Component;

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

@Component("apiResponseBuilderFactory")
public class APIResponseBuilderFactory {
    /*
        String... 이라고 입력하는 것은 가변인자라는 뜻,
        String 객체를 1개 이상 넣을 수 있고 해당 객체들은 배열로 인식된다.
        ( 보통은 갯수를 알 필요가 없는 For 문에서 사용한다. )
    */

    /**
     * APIResponseBuilder 인스턴스 생성
     * @return
     */
    public APIResponseBuilder success(){
        APIResponseBuilder instance = new APIResponseBuilder();
        return instance;
    }




}
