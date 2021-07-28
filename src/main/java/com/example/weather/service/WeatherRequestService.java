package com.example.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherRequestService {
    void requestWeather(String city) throws JsonProcessingException;
}
