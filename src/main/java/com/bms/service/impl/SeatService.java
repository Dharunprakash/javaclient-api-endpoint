package com.bms.service.impl;

import com.bms.dto.SeatDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Seat;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class SeatService {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public SeatService(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<SeatDTO> getAllSeats(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, SeatDTO.class);
    }

    public Seat getSeatById(Long theatreId, Long screenId, Long seatId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, Seat.class);
    }

    public SeatDTO createSeat(Long theatreId, Long screenId, SeatDTO seat) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats";
        String jsonBody = JsonParser.toJson(seat);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, SeatDTO.class);
    }

    public Seat updateSeat(Long theatreId, Long screenId, Long seatId, Seat seat) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        String jsonBody = JsonParser.toJson(seat);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, Seat.class);
    }

    public void deleteSeat(Long theatreId, Long screenId, Long seatId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/seats/" + seatId;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }
}