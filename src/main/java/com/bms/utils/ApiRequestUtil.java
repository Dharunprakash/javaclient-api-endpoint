package com.bms.utils;

import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiRequestUtil {

    private final ApiClient apiClient;

    public ApiRequestUtil(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    public <T> T sendAndParseRequest(String path, String method, String jsonBody, Class<T> clazz) throws ApiException {
        HttpResponseData response;

        switch (method) {
            case "GET":
                response = apiClient.sendGetRequest(path, null);
                break;
            case "POST":
                response = apiClient.sendPostRequest(path, jsonBody, null);
                break;
            case "PUT":
                response = apiClient.sendPutRequest(path, jsonBody, null);
                break;
            case "DELETE":
                response = apiClient.sendDeleteRequest(path, null);
                break;
            default:
                throw new ApiException("Invalid HTTP method: " + method, 0, null);
        }


        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), clazz);
    }
}
