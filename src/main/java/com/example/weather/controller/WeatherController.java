package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.weather.domain.Weather;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
	public class WeatherController {

		@Qualifier( "weatherServiceBean" )
		private final WeatherService weatherService;


		public WeatherController ( WeatherService weatherService ){
			this.weatherService = weatherService;
		}

		
		@RequestMapping( value = "/weather",method=RequestMethod.GET )
		public String setCity( Model model ) {
			List<Weather> weatherList = weatherService.getAll();
			model.addAttribute( "city", "" );
			model.addAttribute( "weatherList", weatherList );
			return "formData";
		}
		
		@RequestMapping( value = "/weather",method=RequestMethod.POST )
		public String getWeather( Model model, @RequestParam String city ) throws JsonProcessingException {
			Weather weather = weatherService.requestWeather( city );
			if( weather != null ) {
				weatherService.saveWeather(weather);
				model.addAttribute( "weatherData", weather );
				return "redirect:weather";
			}else return "errorPage";
		}
	}