package com.example.weather.service;

import com.example.weather.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface WeatherService {
    Optional<Weather> requestWeather(String city) throws JsonProcessingException;

    Weather saveWeather(Weather weather);

    List<Weather> getAll();
}
