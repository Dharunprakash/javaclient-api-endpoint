package com.bms.service;

import com.bms.exception.ApiException;

public interface Service<T> {
    T getAll() throws ApiException;
    T getById(int id) throws ApiException;
    T create(String jsonBody) throws ApiException;
    T update(int id, String jsonBody) throws ApiException;
    T delete(int id) throws ApiException;
}
