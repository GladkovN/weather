package com.example.requestweatherservice.controller;

import com.example.requestweatherservice.service.WeatherRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherRequestService weatherRequestService;

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws IOException {
        weatherRequestService.sendWeatherInKafkaProducer(city);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}