package com.example.weather.kafka;

import com.example.weather.model.Weather;
import com.example.weather.repository.SchemaRepository;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, Weather weather) {
        GenericRecord record = new GenericData.Record(SchemaRepository.instance().getSchemaObject());
        record.put("id", weather.getId());
        record.put("name", weather.getName());
        record.put("weatherDescription", weather.getWeatherDescription());
        record.put("temperature", weather.getTemperature());
        record.put("windSpeed", weather.getWindSpeed());
        kafkaTemplate.send(topic, record);
        kafkaTemplate.flush();
    }
}
