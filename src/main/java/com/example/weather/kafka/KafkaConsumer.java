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
    public void orderListener(ConsumerRecord<Long, Weather> record) throws JsonProcessingException {
        Weather weather = new Weather();
// TODO: 28.07.2021   Тут много навороченно из-за того что я хз почему record.value() как бэ Weather возвращает
//  но при попытке с  ним что-то сделать падает ClassCastExeption и прочие радости
        String jsonWeather = ((Object) record.value()).toString();
        Weather weatherOfJson = new ObjectMapper().readValue(jsonWeather, Weather.class);

        weather.setCity(weatherOfJson.getCity());
        weather.setWindSpeed(weatherOfJson.getWindSpeed());
        weather.setWeatherDescription(weatherOfJson.getWeatherDescription());
        weather.setTemperature(weatherOfJson.getTemperature());

        weatherStorageService.saveWeather(weather);
    }
}
