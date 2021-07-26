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


@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.apikey}")
    private String apikey;

    @Override
    public ResponseEntity<String> requestWeather(String city) {
        String uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .query("q={city name}&appid={API key}")
                .buildAndExpand(city, apikey)
                .toUriString();

        return new RestTemplate().exchange(uri, HttpMethod.GET, null, String.class);
    }

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

}
