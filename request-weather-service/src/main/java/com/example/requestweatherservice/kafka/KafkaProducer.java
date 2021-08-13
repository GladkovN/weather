package com.example.requestweatherservice.kafka;

import com.example.requestweatherservice.model.Weather;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, Weather weather) throws IOException {
        Schema schema = ReflectData.get().getSchema(Weather.class);
        GenericRecord record = new GenericData.Record(schema);

        record.put("id", weather.getId());
        record.put("name", weather.getName());
        record.put("weatherDescription", weather.getWeatherDescription());
        record.put("temperature", weather.getTemperature());
        record.put("windSpeed", weather.getWindSpeed());
        kafkaTemplate.send(topic, record);
        kafkaTemplate.flush();
    }
}
