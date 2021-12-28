package com.mi.bookvillage.common.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Map;

public class APIResponse extends MappingJacksonValue {

    @JsonIgnore
    private Map<String, Object> header;

    public APIResponse(Object value, FilterProvider filterProvider) {
        super(value);
        setFilters(filterProvider);
    }


    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }


    @JsonAnyGetter
    @JsonProperty("header")
    public Map<String, Object> getHeader() {
        return header;
    }

}
