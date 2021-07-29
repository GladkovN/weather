package com.example.weather.controller;

import com.example.weather.service.UserService;
import com.example.weather.service.WeatherRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.weather.model.Weather;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherRequestService weatherRequestService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws JsonProcessingException {
        weatherRequestService.sendWeatherInKafkaProducer(city);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeather() {
        List<Weather> weatherList = userService.getAllWeather();
        return ResponseEntity.status(HttpStatus.OK).body(weatherList);
    }
}