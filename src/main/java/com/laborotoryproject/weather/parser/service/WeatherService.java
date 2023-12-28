package com.laborotoryproject.weather.parser.service;

import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.request_exceptions.CityNotFoundException;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherWithIdNotFoundException;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherWithPressureNotFoundException;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherWithTemperatureNotFoundException;
import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringNotStartWithDigitException;
import com.laborotoryproject.weather.parser.repository.WeatherRepository;
import com.laborotoryproject.weather.parser.util.validate.ValidatingData;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WeatherService {

    WeatherRepository weatherRepository;
    ValidatingData validatingData;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        validatingData = new ValidatingData();
    }

    public Weather findWeatherByCityName(String citiName) {
        if (validatingData.checkStringNotContainsDigit(citiName)) {
            return weatherRepository
                    .findWeatherByCityName(citiName)
                    .orElseThrow(() -> new CityNotFoundException("city not found"));
        } else {
            throw new StringNotStartWithDigitException("string contains digit");
        }
    }

    public Weather findById(long id) {
        return weatherRepository
                .findById(id)
                .orElseThrow(() -> new WeatherWithIdNotFoundException("weather with this id not found"));
    }

    public List<Weather> findByTemperature(String temperature) {
        if (validatingData.checkTemperatureInput(temperature)) {
            return weatherRepository
                    .findWeatherByTemperature(temperature)
                    .orElseThrow(() -> new WeatherWithTemperatureNotFoundException("weather with this temperature not found"));
        } else {
            throw new StringNotStartWithDigitException("string does not start with '+' or '-'");
        }
    }


    public List<Weather> findByPressure(String pressure) {
        if (validatingData.checkStringStartWithDigitAndContainsLetter(pressure)) {
            return weatherRepository
                    .findWeatherByPressure(pressure)
                    .orElseThrow(() -> new WeatherWithPressureNotFoundException("weather with this pressure not found"));
        } else {
            throw new StringNotStartWithDigitException("string not start with digit");
        }
    }

    public List<Weather> findBySpeed(String speed) {
        if (validatingData.checkStringStartWithDigitAndContainsLetter(speed)) {
            return weatherRepository
                    .findWeatherBySpeed(speed)
                    .orElseThrow(() -> new WeatherWithPressureNotFoundException("weather with this speed not found"));
        } else {
            throw new StringNotStartWithDigitException("string not start with digit");
        }
    }

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }

    public List<Weather> findByHumidity(String humidity) {
        if (validatingData.checkStringStartWithDigitAndContainsLetter(humidity)) {
            return weatherRepository
                    .findWeatherByPressure(humidity)
                    .orElseThrow(() -> new WeatherWithPressureNotFoundException("weather with this humidity not found"));
        } else {
            throw new StringNotStartWithDigitException("string not start with digit");
        }
    }

    public void deleteWeatherById(long id) {
        weatherRepository
                .findById(id)
                .ifPresent(weatherRepository::delete);
    }
}
