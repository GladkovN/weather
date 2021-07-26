package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.apikey}")
    private String apikey;

    @Override
    public Optional<Weather> requestWeather(String city) throws JsonProcessingException {

        String uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .query("q={city name}&appid={API key}")
                .buildAndExpand(city, apikey)
                .toUriString();

        try {
            ResponseEntity<String> resp = new RestTemplate().exchange(uri, HttpMethod.GET, null, String.class);
            return Optional.of(new ObjectMapper().readValue(Objects.requireNonNull(resp.getBody()), Weather.class));
        } catch (RestClientException | NullPointerException e) {
            return Optional.empty();
        }
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
