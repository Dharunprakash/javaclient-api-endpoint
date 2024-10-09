package com.bms;

import com.bms.dto.TheatreDTO;
import com.bms.http.ApiClient;
import com.bms.http.ApiClientInstance;
import com.bms.model.Theatre;
import com.bms.service.impl.TheatreService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = ApiClientInstance.getInstance();

        TheatreService theatreService = new TheatreService(apiClient);
        List<TheatreDTO> theatre = theatreService.getAllTheatres();
        List<TheatreDTO> theatre1 = theatreService.getTheatresByLocation("madurai");
        System.out.println(theatre1);

    }
}