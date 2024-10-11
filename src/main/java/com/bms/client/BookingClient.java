package com.bms.client;

import com.bms.dto.BookingDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Booking;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class BookingClient {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public BookingClient(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<BookingDTO> getAllBookings() throws ApiException {
        String path = "/bookings";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, BookingDTO.class);
    }

    public Booking getBookingById(Long id) throws ApiException {
        String path = "/bookings/" + id;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, Booking.class);
    }

    public BookingDTO createBooking(BookingDTO booking) throws ApiException {
        String path = "/bookings";
        String jsonBody = JsonParser.toJson(booking);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, BookingDTO.class);
    }

    public Booking updateBooking(Long id, Booking booking) throws ApiException {
        String path = "/bookings/" + id;
        String jsonBody = JsonParser.toJson(booking);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, Booking.class);
    }

    public void deleteBooking(Long id) throws ApiException {
        String path = "/bookings/" + id;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }
}