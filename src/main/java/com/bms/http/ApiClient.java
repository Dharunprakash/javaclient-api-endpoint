package com.bms.http;

import com.bms.exception.ApiException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class ApiClient {
    private final HttpClient httpClient;
    private final HttpRequestBuilder httpRequestBuilder;

    public ApiClient(String baseUrl) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.httpRequestBuilder = new HttpRequestBuilder(baseUrl);
    }

    public HttpResponseData sendRequest(String path, String method, String jsonBody, Map<String, String> headers) throws ApiException {
        try {
            HttpRequest request = httpRequestBuilder.buildRequest(path, method, jsonBody, headers);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response);
        } catch (Exception e) {
            throw new ApiException(method + " request failed: " + e.getMessage(), 0, null);
        }
    }

    public HttpResponseData sendGetRequest(String path, Map<String, String> headers) throws ApiException {
        return sendRequest(path, "GET", null, headers);
    }

    public HttpResponseData sendPostRequest(String path, String jsonBody, Map<String, String> headers) throws ApiException {
        return sendRequest(path, "POST", jsonBody, headers);
    }

    public HttpResponseData sendPutRequest(String path, String jsonBody, Map<String, String> headers) throws ApiException {
        return sendRequest(path, "PUT", jsonBody, headers);
    }

    public HttpResponseData sendDeleteRequest(String path, Map<String, String> headers) throws ApiException {
        return sendRequest(path, "DELETE", null, headers);
    }

    private HttpResponseData handleResponse(HttpResponse<String> response) throws ApiException {
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return new HttpResponseData(response.statusCode(), response.headers().map(), response.body());
        } else {
            throw new ApiException("Request failed with status code: " + response.statusCode(), response.statusCode(), response.body());
        }
    }
}
