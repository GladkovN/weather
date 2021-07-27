package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.weather.model.Weather;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<Weather>> getWeather() {
        List<Weather> weatherList = weatherService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(weatherList);
    }

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws JsonProcessingException {
        String weatherUrl = weatherService.getWeatherUrl(city);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(weatherUrl, HttpMethod.GET, null, String.class);
        Weather weather = new ObjectMapper().readValue(Objects.requireNonNull(responseEntity.getBody()), Weather.class);
        weatherService.saveWeather(weather);
        return responseEntity;
    }
}