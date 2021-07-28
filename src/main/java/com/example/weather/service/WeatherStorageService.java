package com.example.weather.service;

import com.example.weather.model.Weather;

public interface WeatherStorageService {
    Weather saveWeather(Weather weather);
}
