package com.example.weather.kafka;

import com.example.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Weather> kafkaTemplate;

    public void send(String topic, Weather weather){
        ListenableFuture<SendResult<String, Weather>> future = kafkaTemplate.send(topic,"id",weather);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
