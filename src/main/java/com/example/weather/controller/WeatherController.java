package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.weather.model.Weather;

import java.util.Objects;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        return weatherService.requestWeather(city);
    }

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws JsonProcessingException {
        ResponseEntity<String> responseEntity = weatherService.requestWeather(city);
        Weather weather = new ObjectMapper().readValue(Objects.requireNonNull(responseEntity.getBody()), Weather.class);
        weatherService.saveWeather(weather);
        return responseEntity;
    }
}