package com.bms.service.impl;

import com.bms.dto.BookingDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Booking;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final ApiClient apiClient;

    public BookingService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<BookingDTO> getAllBookings() throws ApiException {
        String path = "/bookings";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

        List<BookingDTO> bookings = new ArrayList<>();
        for (JsonNode node : dataNode) {
            bookings.add(JsonParser.parse(node.toString(), BookingDTO.class));
        }
        return bookings;
    }

    public Booking getBookingById(Long id) throws ApiException {
        String path = "/bookings/" + id;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Booking.class);
    }

    public BookingDTO createBooking(BookingDTO booking) throws ApiException {
        String path = "/bookings";
        String jsonBody = JsonParser.toJson(booking);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), BookingDTO.class);
    }

    public Booking updateBooking(Long id, Booking booking) throws ApiException {
        String path = "/bookings/" + id;
        String jsonBody = JsonParser.toJson(booking);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), Booking.class);
    }
}