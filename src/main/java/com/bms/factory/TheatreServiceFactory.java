package com.bms.factory;

import com.bms.http.ApiClient;

public class TheatreServiceFactory {
    private final ApiClient apiClient;

    public TheatreServiceFactory(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

}
