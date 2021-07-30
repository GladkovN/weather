package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherStorageServiceImpl implements WeatherStorageService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void saveWeather(Weather weather) {
        weatherRepository.save(weather);
    }
}
