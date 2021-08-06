package com.example.userservice.service;

import com.example.userservice.model.Weather;

import java.util.List;

public interface UserService {
    List<Weather> getAllWeather();
}
