package com.example.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherRequestService {
    void sendWeatherInKafkaProducer(String city) throws JsonProcessingException;
}
