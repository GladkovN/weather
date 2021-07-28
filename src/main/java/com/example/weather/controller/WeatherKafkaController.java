package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.service.UserService;
import com.example.weather.service.WeatherRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/getWeather")
public class WeatherKafkaController {
    @Autowired
    WeatherRequestService weatherRequestService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> saveWeather(@RequestParam String city) throws JsonProcessingException {
        weatherRequestService.requestWeather(city);
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeather() {
        List<Weather> weatherList = userService.getAllWeather();
        return ResponseEntity.status(HttpStatus.OK).body(weatherList);
    }
}
