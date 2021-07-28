package com.example.weather.service;

import com.example.weather.kafka.KafkaProducer;
import com.example.weather.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
@Service
public class WeatherRequestServiceImpl implements WeatherRequestService {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.apikey}")
    private String apikey;

    @Override
    public void requestWeather(String city) throws JsonProcessingException {
      String weatherUrl = UriComponentsBuilder
              .newInstance()
              .scheme("https")
              .host(url)
              .query("q={city name}&appid={API key}")
              .buildAndExpand(city, apikey)
              .toUriString();
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(weatherUrl, HttpMethod.GET, null, String.class);
        Weather weather = new ObjectMapper().readValue(Objects.requireNonNull(responseEntity.getBody()), Weather.class);
        kafkaProducer.send("weather",weather);
    }
}