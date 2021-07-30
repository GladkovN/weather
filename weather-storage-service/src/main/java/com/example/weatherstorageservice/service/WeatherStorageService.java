package com.example.weatherstorageservice.service;

import com.example.weatherstorageservice.model.Weather;

public interface WeatherStorageService {
    void saveWeather(Weather weather);
}
