package com.example.userservice.service;

import com.example.userservice.model.Weather;
import com.example.userservice.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }
}
