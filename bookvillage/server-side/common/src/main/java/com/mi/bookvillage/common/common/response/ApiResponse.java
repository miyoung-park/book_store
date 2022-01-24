package com.mi.bookvillage.common.common.response;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Map;

public class ApiResponse extends MappingJacksonValue {

    @JsonIgnore
    private Map<String, Object> header;

    public ApiResponse(Object value) {
        super(value);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }


    /**
     * JsonAnyGetter : Map 필드에 유연성을 제공 ( 동일한 key-value 형태로 .. )
     * JsonProperty : 직렬화 시 설정할 수 있는 이름을 지정 (ex. DB 컬럼명도 변경없이 VO 에서 JsonProperty 로 설정할 수 있다. )
     */
    @JsonAnyGetter
    @JsonProperty("header")
    public Map<String, Object> getHeader() {
        return header;
    }
}
