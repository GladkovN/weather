package com.example.weather.repository;

import org.apache.avro.Schema;

public class SchemaRepository {
    private static final String SCHEMA = "{\"namespace\": \"avro\",\n" +
            "\"type\": \"record\",\n" +
            "\"name\": \"Weather\",\n" +
            "\"fields\": [\n" +
            "     {\"name\": \"id\",  \"type\": [\"long\", \"null\"]},\n" +
            "     {\"name\": \"name\", \"type\": \"string\"},\n" +
            "     {\"name\": \"weatherDescription\", \"type\": \"string\"},\n" +
            "     {\"name\": \"temperature\",  \"type\": [\"double\", \"null\"]},\n" +
            "     {\"name\": \"windSpeed\",  \"type\": [\"double\", \"null\"]}\n" +
            "]\n" +
            "}\n";

    private static final Schema SCHEMA_OBJECT = new Schema.Parser().parse(SCHEMA);

    private static SchemaRepository INSTANCE = new SchemaRepository();

    public static SchemaRepository instance() {
        return INSTANCE;
    }

    public Schema getSchemaObject() {
        return SCHEMA_OBJECT;
    }
}
