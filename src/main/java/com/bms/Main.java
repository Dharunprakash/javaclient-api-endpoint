package com.bms;

import com.bms.dto.*;
import com.bms.http.ApiClient;
import com.bms.http.ApiClientInstance;
import com.bms.client.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = ApiClientInstance.getInstance();

        TheatreService theatreService = new TheatreService(apiClient);
        List<TheatreDTO> theatres = theatreService.getAllTheatres();
        for (TheatreDTO theatre : theatres) {
            System.out.println(theatre);
        }

        MovieService movieService = new MovieService(apiClient);
        List<MovieDTO> movies = movieService.getAllMovies();
        for (MovieDTO movie : movies) {
            System.out.println(movie);
        }



    }
}