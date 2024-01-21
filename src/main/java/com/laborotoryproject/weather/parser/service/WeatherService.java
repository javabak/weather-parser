package com.laborotoryproject.weather.parser.service;

import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.request_exceptions.*;
import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringContainsDigitException;
import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringNotStartWithDigitException;
import com.laborotoryproject.weather.parser.repository.WeatherRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.laborotoryproject.weather.parser.util.validate.ValidatingData.*;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WeatherService {

    WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather findById(long id) {
        if (checkId(String.valueOf(id))) {
            return weatherRepository
                    .findById(id)
                    .orElseThrow(() -> new WeatherNotFound("weather with this id not found"));
        } else {
            throw new StringNotStartWithDigitException("id contains letters");
        }
    }

    public Weather findWeatherByCityName(String cityName) {
        if (checkStringNotContainsDigit(cityName)) {
            return weatherRepository
                    .findWeatherByCityName(cityName)
                    .orElseThrow(() -> new CityNotFoundException("city not found"));
        } else {
            throw new StringContainsDigitException("cityName contains digit");
        }
    }


    public List<Weather> findAllWeathersByTemperature(String temperature) {
        if (checkTemperatureInput(temperature)) {
            return weatherRepository.findAllWeathersByTemperature(temperature)
                    .filter(weathers -> weathers.stream().findFirst().isPresent())
                    .orElseThrow(() -> new WeatherNotFound("weather with this temperature not found"));
        } else {
            throw new StringNotStartWithDigitException("temperature contains letters");
        }
    }


    public List<Weather> findAllWeathersByPressure(String pressure) {
        if (checkStringStartWithDigitAndContainsLetter(pressure)) {
            return weatherRepository.findAllWeathersByPressure(pressure)
                    .filter(weathers -> weathers.stream().findFirst().isPresent())
                    .orElseThrow(() -> new WeatherNotFound("weather with this pressure not found"));
        } else {
            throw new StringNotStartWithDigitException("pressure not start with digit or don't contains 'мм'");
        }
    }

    public List<Weather> findAllWeathersBySpeed(String speed) {
        if (checkStringStartWithDigitAndContainsLetter(speed)) {
            return weatherRepository.findAllWeathersBySpeed(speed)
                    .filter(weathers -> weathers.stream().findFirst().isPresent())
                    .orElseThrow(() -> new WeatherNotFound("weather with this speed not found"));
        } else {
            throw new StringNotStartWithDigitException("speed not start with digit or don't contains 'мc'");
        }
    }

    public List<Weather> findAllWeathersByHumidity(String humidity) {
        if (checkStringForHumidity(humidity)) {
            return weatherRepository.findAllWeathersByHumidity(humidity)
                    .filter(weathers -> weathers.stream().findFirst().isPresent())
                    .orElseThrow(() -> new WeatherNotFound("weather with this humidity not found"));
        } else {
            throw new StringNotStartWithDigitException("humidity contains letters");
        }
    }


    public void deleteWeatherById(long id) {
        if (checkId(String.valueOf(id))) {
            weatherRepository
                    .findById(id)
                    .ifPresent(weatherRepository::delete);
        } else {
            throw new StringNotStartWithDigitException("id contains letters");
        }
    }

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }
}
