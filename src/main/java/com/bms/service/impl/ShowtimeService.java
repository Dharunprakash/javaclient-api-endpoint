package com.bms.service.impl;

import com.bms.dto.ShowtimeDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Showtime;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class ShowtimeService {
    private final ApiClient apiClient;

    public ShowtimeService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ShowtimeDTO> getAllShowtimes(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");


        List<ShowtimeDTO> showtimes = new ArrayList<>();
        for (JsonNode node : dataNode) {
            showtimes.add(JsonParser.parse(node.toString(), ShowtimeDTO.class));
        }
        return showtimes;
    }

    public Showtime getShowtimeById(Long theatreId, Long screenId, Long showtimeId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Showtime.class);
    }

    public ShowtimeDTO createShowtime(Long theatreId, Long screenId, ShowtimeDTO showtime) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes";
        String jsonBody = JsonParser.toJson(showtime);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), ShowtimeDTO.class);
    }

    public Showtime updateShowtime(Long theatreId, Long screenId, Long showtimeId, Showtime showtime) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        String jsonBody = JsonParser.toJson(showtime);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Showtime.class);
    }

    public void deleteShowtime(Long theatreId, Long screenId, Long showtimeId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        apiClient.sendDeleteRequest(path, null);
    }
}