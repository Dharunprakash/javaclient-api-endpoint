package com.bms.utils;

import com.bms.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parse(String json, Class<T> clazz) throws ApiException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to parse JSON", 0, json);
        }
    }

    public static <T> T parse(String json, TypeReference<T> typeReference) throws ApiException {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to parse JSON", 0, json);
        }
    }

    public static String toJson(Object object) throws ApiException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to convert object to JSON", 0, null);
        }
    }

    public static JsonNode readTree(String json) throws ApiException {
        try {
            System.out.println(json);
            return objectMapper.readTree(json.trim());

        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to read JSON tree", 0, json);
        }
    }
}
