package com.bms.service.impl;

import com.bms.dto.SeatDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Seat;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class SeatService {
    private final ApiClient apiClient;

    public SeatService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<SeatDTO> getAllSeats(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

        List<SeatDTO> seats = new ArrayList<>();
        for (JsonNode node : dataNode) {
            seats.add(JsonParser.parse(node.toString(), SeatDTO.class));
        }
        return seats;
    }

    public Seat getSeatById(Long theatreId, Long screenId, Long seatId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Seat.class);
    }

    public SeatDTO createSeat(Long theatreId, Long screenId, SeatDTO seat) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats";
        String jsonBody = JsonParser.toJson(seat);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), SeatDTO.class);
    }

    public Seat updateSeat(Long theatreId, Long screenId, Long seatId, Seat seat) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        String jsonBody = JsonParser.toJson(seat);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Seat.class);
    }

    public void deleteSeat(Long theatreId, Long screenId, Long seatId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        apiClient.sendDeleteRequest(path, null);
    }
}