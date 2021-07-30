package com.example.weather.model;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class AvroGenericRecordDeserializer implements Deserializer {
    private Schema schema = null;

    @Override
    public void configure(Map configs, boolean isKey) {
        schema = (Schema) configs.get("SCHEMA");
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        SeekableByteArrayInput arrayInput = new SeekableByteArrayInput(data);
        GenericRecord record = null;
        try {
            DataFileReader<GenericRecord>  dataFileReader = new DataFileReader<>(arrayInput, datumReader);
            record = dataFileReader.next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return record;
    }
}
