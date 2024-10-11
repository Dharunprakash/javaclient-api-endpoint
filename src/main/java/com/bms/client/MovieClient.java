package com.bms.client;

import com.bms.dto.MovieDTO;
import com.bms.exception.ApiException;
import com.bms.http.ApiClient;
import com.bms.model.Movie;
import com.bms.utils.ApiRequestUtil;
import com.bms.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class MovieClient {
    private final ApiRequestUtil apiRequestUtil;
    private final JsonParser jsonParser;

    public MovieClient(ApiClient apiClient) {
        this.apiRequestUtil = new ApiRequestUtil(apiClient);
        this.jsonParser = new JsonParser();
    }

    public List<MovieDTO> getAllMovies() throws ApiException {
        String path = "/movies";
        JsonNode dataNode = apiRequestUtil.sendAndParseRequest(path, "GET", null, JsonNode.class);
        return jsonParser.parseJsonArray(dataNode, MovieDTO.class);
    }

    public MovieDTO getMovieById(Long id) throws ApiException {
        String path = "/movies/" + id;
        return apiRequestUtil.sendAndParseRequest(path, "GET", null, MovieDTO.class);
    }

    public MovieDTO createMovie(MovieDTO movie) throws ApiException {
        String path = "/movies";
        String jsonBody = JsonParser.toJson(movie);
        return apiRequestUtil.sendAndParseRequest(path, "POST", jsonBody, MovieDTO.class);
    }

    public MovieDTO updateMovie(Long id, Movie movie) throws ApiException {
        String path = "/movies/" + id;
        String jsonBody = JsonParser.toJson(movie);
        return apiRequestUtil.sendAndParseRequest(path, "PUT", jsonBody, MovieDTO.class);
    }

    public void deleteMovie(Long id) throws ApiException {
        String path = "/movies/" + id;
        apiRequestUtil.sendAndParseRequest(path, "DELETE", null, Void.class);
    }
}