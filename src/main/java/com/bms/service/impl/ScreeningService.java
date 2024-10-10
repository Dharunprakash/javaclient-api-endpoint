package com.bms.service.impl;

import com.bms.dto.ScreeningDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class ScreeningService {
    private final ApiClient apiClient;

    public ScreeningService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ScreeningDTO> getScreeningsByMovieId(Long movieId) throws ApiException {
        String path = "/movies/" + movieId + "/screenings";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        System.out.println(dataNode);
        List<ScreeningDTO> screenings = new ArrayList<>();
        for (JsonNode node : dataNode) {
            ScreeningDTO screening = JsonParser.parse(node.toString(), ScreeningDTO.class);
            screenings.add(screening);
        }
        return screenings;
    }
}