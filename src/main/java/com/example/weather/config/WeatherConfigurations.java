package com.example.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.weather.domain.WeatherUrl;

@Configuration
@PropertySource( "classpath:application.properties" )
public class WeatherConfigurations {
	
	@Value( "${weather.url}" )
	private String url;
	
	@Value( "${weather.apikey}" )
	private String apikey;

	@Bean
	public WeatherUrl weatherUrl() {
		WeatherUrl weatherUrl = new WeatherUrl();
		weatherUrl.setUrl(url);
		weatherUrl.setApiKey(apikey);
		return weatherUrl;
	}
}
