package com.example.weatherstorageservice.kafka;

import com.example.weatherstorageservice.model.Weather;
import com.example.weatherstorageservice.service.WeatherStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.generic.GenericData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private WeatherStorageService weatherStorageService;

    @KafkaListener(topics = "weather")
    public void orderListener(ConsumerRecord<Long, GenericData.Record> record) throws JsonProcessingException {
        Weather weather = new ObjectMapper().readValue(record.value().toString(), Weather.class);
        weatherStorageService.saveWeather(weather);
    }
}
