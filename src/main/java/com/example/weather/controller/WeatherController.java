package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.weather.model.Weather;

import java.util.List;

@Controller
@RequestMapping(value = "/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String setCity(Model model) {
        List<Weather> weatherList = weatherService.getAll();
        model.addAttribute("city", "");
        model.addAttribute("weatherList", weatherList);
        return "setCityForm";
    }

    @PostMapping
    public String getWeather(Model model, @RequestParam String city) throws JsonProcessingException {
        Weather weather = weatherService.requestWeather(city);
        if (weather != null) {
            weatherService.saveWeather(weather);
            model.addAttribute("weatherData", weather);
            return "redirect:weather";
        } else return "errorPage";
    }
}