package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.weather.model.Weather;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/weather")
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
        Optional<Weather> weatherOptional = weatherService.requestWeather(city);
        if (weatherOptional.isPresent()) {
            weatherService.saveWeather(weatherOptional.get());
            model.addAttribute("weatherData", weatherOptional.get());
            return "redirect:";
        } else return "errorPage";
    }
}