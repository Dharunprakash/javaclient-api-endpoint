package com.bms.service.impl;

import com.bms.dto.TheatreDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Theatre;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class TheatreService {
    private final ApiClient apiClient;

    public TheatreService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<TheatreDTO> getAllTheatres() throws ApiException {
        String path = "/theatres";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

       List<TheatreDTO> theatre = new ArrayList<>();;
        for (JsonNode node : dataNode) {
            theatre.add(JsonParser.parse(node.toString(), TheatreDTO.class));
        }
        return theatre;
    }

    public Theatre getTheatreById(Long id) throws ApiException {
        String path = "/theatres/" + id;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Theatre.class);
    }

    public TheatreDTO createTheatre(TheatreDTO theatre) throws ApiException {
        String path = "/theatres";
        String jsonBody = JsonParser.toJson(theatre);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), TheatreDTO.class);
    }

    public Theatre updateTheatre(Long id, Theatre theatre) throws ApiException {
        String path = "/theatres/" + id;
        String jsonBody = JsonParser.toJson(theatre);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Theatre.class);
    }

    public void deleteTheatre(Long id) throws ApiException {
        String path = "/theatres/" + id;
        apiClient.sendDeleteRequest(path, null);
    }

    public List<TheatreDTO> getTheatresByLocation(String location) throws ApiException {
        String path = "/theatres?location=" + location;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        List<TheatreDTO> theatre = new ArrayList<>();
        for (JsonNode node : dataNode) {
            theatre.add(JsonParser.parse(node.toString(), TheatreDTO.class));
        }
        return theatre;
    }
}
