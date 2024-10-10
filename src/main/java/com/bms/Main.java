package com.bms;

import com.bms.dto.ScreeningDTO;
import com.bms.dto.ScreenShowtimeDTO;
import com.bms.dto.ShowDTO;
import com.bms.http.ApiClient;
import com.bms.http.ApiClientInstance;
import com.bms.service.impl.ScreeningService;
import com.bms.service.impl.ShowService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = ApiClientInstance.getInstance();

        ShowService showService = new ShowService(apiClient);
       List<ShowDTO> shows = showService.getAllShows(1L);
        System.out.println(shows);
    }
}