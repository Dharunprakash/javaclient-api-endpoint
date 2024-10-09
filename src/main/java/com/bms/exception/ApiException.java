package com.bms.exception;


public class ApiException extends Exception {
    private int statusCode;
    private String responseBody;

    public ApiException(String message, int statusCode, String responseBody) {
        super(message + " | Status Code: " + statusCode);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
