package com.bms.service.impl;

import com.bms.dto.MovieDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.http.HttpResponseData;
import com.bms.model.Movie;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private final ApiClient apiClient;

    public MovieService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<MovieDTO> getAllMovies() throws ApiException {
        String path = "/movies";
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");

        List<MovieDTO> movies = new ArrayList<>();
        for (JsonNode node : dataNode) {
            movies.add(JsonParser.parse(node.toString(), MovieDTO.class));
        }
        return movies;
    }

    public MovieDTO getMovieById(Long id) throws ApiException {
        String path = "/movies/" + id;
        HttpResponseData response = apiClient.sendGetRequest(path, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), MovieDTO.class);
    }

    public MovieDTO createMovie(MovieDTO movie) throws ApiException {
        String path = "/movies";
        String jsonBody = JsonParser.toJson(movie);
        HttpResponseData response = apiClient.sendPostRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), MovieDTO.class);
    }

    public MovieDTO updateMovie(Long id, Movie movie) throws ApiException {
        String path = "/movies/" + id;
        String jsonBody = JsonParser.toJson(movie);
        HttpResponseData response = apiClient.sendPutRequest(path, jsonBody, null);
        JsonNode rootNode = JsonParser.readTree(response.getBody());
        JsonNode dataNode = rootNode.get("data");
        return JsonParser.parse(dataNode.toString(), MovieDTO.class);
    }

    public void deleteMovie(Long id) throws ApiException {
        String path = "/movies/" + id;
        apiClient.sendDeleteRequest(path, null);
    }


}