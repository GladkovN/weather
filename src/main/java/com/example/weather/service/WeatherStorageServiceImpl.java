package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherStorageServiceImpl implements WeatherStorageService {
    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }
}
