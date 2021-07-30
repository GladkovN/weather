package com.example.weather.kafka;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private WeatherStorageService weatherStorageService;

    @KafkaListener(topics = "weather")
    public void orderListener(ConsumerRecord<Long, Object> record) throws JsonProcessingException {
        Weather weather = new Weather();
        Object objWeather = record.value();
        String jsonWeather = objWeather.toString();
        Weather weatherOfJson = new ObjectMapper().readValue(jsonWeather, Weather.class);
        weather.setName(weatherOfJson.getName());
        weather.setWindSpeed(weatherOfJson.getWindSpeed());
        weather.setWeatherDescription(weatherOfJson.getWeatherDescription());
        weather.setTemperature(weatherOfJson.getTemperature());

        weatherStorageService.saveWeather(weather);
    }
}
