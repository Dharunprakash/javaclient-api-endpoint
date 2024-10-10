package com.bms.service.impl;

import com.bms.dto.ShowDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class ShowService {
    private final ApiClient apiClient;

    public ShowService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ShowDTO> getAllShows(Long theatreId) throws ApiException {
        String path = "/theatres/" + theatreId + "/shows";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

        List<ShowDTO> shows = new ArrayList<>();
        for (JsonNode node : dataNode) {
            ShowDTO show = JsonParser.parse(node.toString(), ShowDTO.class);
            shows.add(show);
        }
        return shows;
    }
}