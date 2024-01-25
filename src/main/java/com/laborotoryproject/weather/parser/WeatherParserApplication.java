package com.laborotoryproject.weather.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WeatherParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherParserApplication.class, args);
	}

}
