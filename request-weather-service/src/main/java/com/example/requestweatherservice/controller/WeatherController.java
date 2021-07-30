package com.example.requestweatherservice.controller;

import com.example.requestweatherservice.service.WeatherRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherRequestService weatherRequestService;

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws JsonProcessingException {
        weatherRequestService.sendWeatherInKafkaProducer(city);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}