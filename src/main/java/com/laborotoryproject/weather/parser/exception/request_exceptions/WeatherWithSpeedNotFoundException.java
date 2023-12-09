package com.laborotoryproject.weather.parser.exception.request_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WeatherWithSpeedNotFoundException extends RuntimeException {
    public WeatherWithSpeedNotFoundException(String message) {
        super(message);
    }
}
