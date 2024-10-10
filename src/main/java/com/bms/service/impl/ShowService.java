package com.bms.service.impl;

import com.bms.dto.ShowDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ShowService {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public ShowService(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<ShowDTO> getAllShows(Long theatreId) throws ApiException {
        String path = "/theatres/" + theatreId + "/shows";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, ShowDTO.class);
    }
}