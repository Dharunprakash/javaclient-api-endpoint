package com.bms;

import com.bms.http.ApiClient;
import com.bms.http.ApiClientInstance;
import com.bms.http.HttpResponseData;
import com.bms.exception.ApiException;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = ApiClientInstance.getInstance();
    }
}
