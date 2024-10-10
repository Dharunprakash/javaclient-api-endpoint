package com.bms.service.impl;

import com.bms.dto.TheatreDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Theatre;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class TheatreService {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public TheatreService(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }


    public List<TheatreDTO> getAllTheatres() throws ApiException {
        String path = "/theatres";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, TheatreDTO.class);
    }

    public Theatre getTheatreById(Long id) throws ApiException {
        String path = "/theatres/" + id;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, Theatre.class);
    }

    public TheatreDTO createTheatre(TheatreDTO theatre) throws ApiException {
        String path = "/theatres";
        String jsonBody = JsonParser.toJson(theatre);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, TheatreDTO.class);
    }

    public Theatre updateTheatre(Long id, Theatre theatre) throws ApiException {
        String path = "/theatres/" + id;
        String jsonBody = JsonParser.toJson(theatre);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, Theatre.class);
    }

    public void deleteTheatre(Long id) throws ApiException {
        String path = "/theatres/" + id;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }

    public List<TheatreDTO> getTheatresByLocation(String location) throws ApiException {
        String path = "/theatres?location=" + location;
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, TheatreDTO.class);
    }
}
