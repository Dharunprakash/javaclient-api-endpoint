package com.bms.client;

import com.bms.dto.ScreeningDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ScreeningClient {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public ScreeningClient(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<ScreeningDTO> getScreeningsByMovieId(Long movieId) throws ApiException {
        String path = "/movies/" + movieId + "/screenings";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, ScreeningDTO.class);
    }
}