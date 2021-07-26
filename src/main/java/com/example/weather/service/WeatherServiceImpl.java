package com.example.weather.service;

import com.example.weather.domain.Weather;
import com.example.weather.domain.WeatherUrl;
import com.example.weather.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Service( "weatherServiceBean" )
public class WeatherServiceImpl implements WeatherService{

    private final WeatherUrl weatherUrl;
    private final WeatherRepository weatherRepository;


    public WeatherServiceImpl ( WeatherUrl weatherUrl, WeatherRepository weatherRepository ){
        this.weatherUrl = weatherUrl;
        this.weatherRepository = weatherRepository;
    }


    @Override
    public Weather requestWeather( String city ) throws JsonProcessingException {

        String uri = UriComponentsBuilder
                .newInstance()
                .scheme( "https" )
                .host( weatherUrl.getUrl() )
                .query( "q={city name}&appid={API key}" )
                .buildAndExpand( city, weatherUrl.getApiKey() )
                .toUriString();

    try {
        ResponseEntity<String> resp = new RestTemplate().exchange( uri, HttpMethod.GET, null, String.class );
        return new ObjectMapper().readValue( Objects.requireNonNull( resp.getBody() ), Weather.class );
        } catch ( RestClientException  | NullPointerException e ){ return null; }
    }

    @Override
    public Weather saveWeather( Weather weather ) {
        return weatherRepository.save( weather );
    }

    @Override
    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }
}
