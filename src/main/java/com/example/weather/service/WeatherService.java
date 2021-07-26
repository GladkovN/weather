package com.example.weather.service;

import com.example.weather.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<String> requestWeather(String city);

    Weather saveWeather(Weather weather);
}
