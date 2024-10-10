package com.bms.utils;

import com.bms.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

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
            return objectMapper.readTree(json);

        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to read JSON tree", 0, json);
        }
    }
    public <T> List<T> parseJsonArray(JsonNode dataNode, Class<T> clazz) {
        return StreamSupport.stream(dataNode.spliterator(), false)
                .map(node -> {
                    try {
                        return JsonParser.parse(node.toString(), clazz);
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
