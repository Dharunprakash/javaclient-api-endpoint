package com.bms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();



    public static <T> T parseJson(BufferedReader reader, Class<T> clazz) throws IOException {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        return parse(json.toString(), clazz);
    }


    public static <T> T parse(Map<String, Object> data, Class<T> clazz) {
        return objectMapper.convertValue(data, clazz);
    }


    public static <T> T parse(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }
}