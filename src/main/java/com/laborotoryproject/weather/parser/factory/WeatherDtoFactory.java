package com.laborotoryproject.weather.parser.factory;

import com.laborotoryproject.weather.parser.dto.WeatherDto;
import com.laborotoryproject.weather.parser.entity.Weather;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<WeatherDto> makeListDto(List<Weather> weathers) {
        return weathers.stream().map(this::makeDto).collect(Collectors.toList());
    }
}
