package com.bms.client;

import com.bms.dto.ShowtimeDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Showtime;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ShowtimeClient {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public ShowtimeClient(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<ShowtimeDTO> getAllShowtimes(Long theatreId, Long screenId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, ShowtimeDTO.class);
    }

    public Showtime getShowtimeById(Long theatreId, Long screenId, Long showtimeId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, Showtime.class);
    }

    public ShowtimeDTO createShowtime(Long theatreId, Long screenId, ShowtimeDTO showtime) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes";
        String jsonBody = JsonParser.toJson(showtime);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, ShowtimeDTO.class);
    }

    public Showtime updateShowtime(Long theatreId, Long screenId, Long showtimeId, Showtime showtime) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        String jsonBody = JsonParser.toJson(showtime);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, Showtime.class);
    }

    public void deleteShowtime(Long theatreId, Long screenId, Long showtimeId) throws ApiException {
        String path = "/theatres/" + theatreId + "/screens/" + screenId + "/showtimes/" + showtimeId;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }
}