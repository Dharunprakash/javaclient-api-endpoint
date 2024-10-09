package com.bms;

import com.bms.dto.MovieDTO;
import com.bms.dto.TheatreDTO;
import com.bms.http.ApiClient;
import com.bms.http.ApiClientInstance;
import com.bms.model.Movie;
import com.bms.model.Theatre;
import com.bms.service.impl.MovieService;
import com.bms.service.impl.TheatreService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = ApiClientInstance.getInstance();

//        TheatreService theatreService = new TheatreService(apiClient);
//         Theatre theatre = theatreService.getTheatreById(1L);
//        List<TheatreDTO> theatre1 = theatreService.getTheatresByLocation("madurai");

        MovieService movieService = new MovieService(apiClient);

        MovieDTO movie = movieService.getMovieById(1L);
        System.out.println(movie);

//        System.out.println(theatre);

    }
}