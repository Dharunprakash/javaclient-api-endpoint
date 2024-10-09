package com.bms.http;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponseData {
    private int statusCode;
    private Map<String, List<String>> headers;
    private String body;

}
