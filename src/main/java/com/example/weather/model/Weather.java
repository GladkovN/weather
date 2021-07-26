package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String weatherDescription;

    @Column(name = "city")
    private String city;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "speed")
    private double windSpeed;

    @JsonProperty("speed")
    private void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("wind")
    public void setSpeed(Map<String, Object> wind) {
        setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
    }

    @JsonProperty("name")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherDescription((String) weather.get("description"));
    }

    @JsonProperty("temp")
    public double getTemperature() {
        return temperature;
    }

    @JsonProperty("temp")
    private void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("main")
    public void setTemp(Map<String, Object> main) {
        double scale = Math.pow(10, 1);
        setTemperature(Math.ceil((((double) main.get("temp") - 273) * scale)) / scale);
    }
}
