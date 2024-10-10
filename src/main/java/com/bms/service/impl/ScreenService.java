package com.bms.service.impl;

import com.bms.dto.ScreenDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Screen;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class ScreenService {
    private final ApiClient apiClient;

    public ScreenService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ScreenDTO> getAllScreens(Long theatreId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

        List<ScreenDTO> screens = new ArrayList<>();
        for (JsonNode node : dataNode) {
            screens.add(JsonParser.parse(node.toString(), ScreenDTO.class));
        }
        return screens;
    }

    public Screen getScreenById(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Screen.class);
    }

    public ScreenDTO createScreen(Long theatreId, ScreenDTO screen) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens";
        String jsonBody = JsonParser.toJson(screen);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), ScreenDTO.class);
    }

    public Screen updateScreen(Long theatreId, Long screenId, Screen screen) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        String jsonBody = JsonParser.toJson(screen);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Screen.class);
    }

    public void deleteScreen(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        apiClient.sendDeleteRequest(path, null);
    }
}