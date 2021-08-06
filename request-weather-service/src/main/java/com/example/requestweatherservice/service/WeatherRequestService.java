package com.example.requestweatherservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface WeatherRequestService {
    void sendWeatherInKafkaProducer(String city) throws JsonProcessingException, IOException;
}
