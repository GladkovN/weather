package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.apikey}")
    private String apikey;

    @Override
    public String getWeatherUrl(String city) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .query("q={city name}&appid={API key}")
                .buildAndExpand(city, apikey)
                .toUriString();

    }

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }

}
