package com.example.requestweatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Weather {
    private Long id;

    @JsonProperty("name")
    private String name;

    private String weatherDescription;

    private double temperature;

    private double windSpeed;

    @JsonProperty("wind")
    private void getWindSpeedOfJson(Map<String, Object> wind) {
        setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
    }

    @JsonProperty("weather")
    private void getWeatherDescriptionOfJson(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherDescription((String) weather.get("description"));
    }

    @JsonProperty("main")
    private void getTemperatureOfJson(Map<String, Object> main) {
        double scale = Math.pow(10, 1);
        setTemperature(Math.ceil((((double) main.get("temp") - 273) * scale)) / scale);
    }

}
