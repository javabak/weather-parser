package com.laborotoryproject.weather.parser.factory;

import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.dto.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherDtoFactory {

    public WeatherDto makeDto(Weather weather) {
        return WeatherDto
                .builder()
                .cityName(weather.getCityName())
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .speed(weather.getSpeed())
                .temperature(weather.getTemperature())
                .build();
    }
}
