package com.example.weather.service;

import com.example.weather.model.Weather;

import java.util.List;

public interface WeatherService {
    String getWeatherUrl(String city);

    Weather saveWeather(Weather weather);

    List<Weather> getAll();
}
