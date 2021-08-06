package com.example.weatherstorageservice.service;

import com.example.weatherstorageservice.model.Weather;
import com.example.weatherstorageservice.repository.WeatherRepository;
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
