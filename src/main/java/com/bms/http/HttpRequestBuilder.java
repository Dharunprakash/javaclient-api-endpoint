package com.bms.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

public class HttpRequestBuilder {
    private final String baseUrl;

    public HttpRequestBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public HttpRequest buildRequest(String path, String method, String jsonBody, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path));

        switch (method.toUpperCase()) {
            case "GET":
                requestBuilder.GET();
                break;
            case "POST":
                requestBuilder.header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
                break;
            case "PUT":
                requestBuilder.header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(jsonBody));
                break;
            case "DELETE":
                requestBuilder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }

        return requestBuilder.build();
    }
}
