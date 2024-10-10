package com.bms.service.impl;

import com.bms.dto.ScreenDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Screen;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ScreenService {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public ScreenService(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<ScreenDTO> getAllScreens(Long theatreId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, ScreenDTO.class);
    }

    public Screen getScreenById(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, Screen.class);
    }

    public ScreenDTO createScreen(Long theatreId, ScreenDTO screen) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens";
        String jsonBody = JsonParser.toJson(screen);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, ScreenDTO.class);
    }

    public Screen updateScreen(Long theatreId, Long screenId, Screen screen) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        String jsonBody = JsonParser.toJson(screen);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, Screen.class);
    }

    public void deleteScreen(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }
}