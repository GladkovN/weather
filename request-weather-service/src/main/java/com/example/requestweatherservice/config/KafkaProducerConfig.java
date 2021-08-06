package com.example.requestweatherservice.config;

import com.example.requestweatherservice.model.AvroGenericRecordSerializer;
import com.example.requestweatherservice.model.Weather;
import com.example.requestweatherservice.repository.SchemaRepository;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.properties.schema}")
    private String avroSchema;


    @Bean
    public ProducerFactory<Long, Weather> greetingProducerFactory() throws IOException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroGenericRecordSerializer.class);
        //configProps.put("SCHEMA", new Schema.Parser().parse(new File(avroSchema)));
        configProps.put("SCHEMA", SchemaRepository.instance().getSchemaObject());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Long, Weather> greetingKafkaTemplate() throws IOException {
        return new KafkaTemplate<>(greetingProducerFactory());
    }

}
