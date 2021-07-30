package com.example.requestweatherservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherRequestService {
    void sendWeatherInKafkaProducer(String city) throws JsonProcessingException;
}
