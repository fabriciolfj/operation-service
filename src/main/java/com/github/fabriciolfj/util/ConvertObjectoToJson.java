package com.github.fabriciolfj.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObjectoToJson<T> {

    private ObjectMapper mapper = new ObjectMapper();

    public String toJson(final T clazz) {
        try {
            return mapper.writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
