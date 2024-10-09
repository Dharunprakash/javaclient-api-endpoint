package com.bms.http;

public class ApiClientInstance {

    private static volatile ApiClient instance;

    private ApiClientInstance() {
    }


    public static ApiClient getInstance() {
        if (instance == null) {
            synchronized (ApiClientInstance.class) {
                if (instance == null) {
                    String baseUrl = "http://localhost:8080/bms_war_exploded/api";
                    if (baseUrl == null || baseUrl.isEmpty()) {
                        throw new IllegalStateException("API_BASE_URL environment variable not set");
                    }
                    instance = new ApiClient(baseUrl);
                }
            }
        }
        return instance;
    }

}
