package com.example.weather.controller;

import com.example.weather.service.WeatherRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/getWeather")
public class WeatherKafkaController {
    @Autowired
    WeatherRequestService weatherRequestService;

    @GetMapping
    public ResponseEntity<String> getWeather(@RequestParam String city) throws JsonProcessingException {
        weatherRequestService.requestWeather( city );
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}
