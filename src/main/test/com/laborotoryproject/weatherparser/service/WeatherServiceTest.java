package com.laborotoryproject.weatherparser.service;


import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.request_exceptions.*;
import com.laborotoryproject.weather.parser.repository.WeatherRepository;
import com.laborotoryproject.weather.parser.service.WeatherService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherServiceTest {

    @Mock
    WeatherRepository weatherRepository;

    @InjectMocks
    WeatherService weatherService;

    @Test
    public void findById_whenIdExists_shouldReturnWeather() {
        Weather weather = mock(Weather.class);
        weather.setId(1);
        when(weatherRepository.findById(1L)).thenReturn(Optional.of(weather));

        Weather result = weatherService.findById(1L);

        assertEquals(weather, result);
        verify(weatherRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_whenIdDoesNotExist_shouldThrowWeatherWithIdNotFoundException() {
        when(weatherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WeatherWithIdNotFoundException.class, () -> weatherService.findById(1L));
        verify(weatherRepository, times(1)).findById(1L);
    }

    @Test
    public void findByTemperature_validTemperature_returnWeather() {
        String temperature = "25.5";
        Weather expectedWeather = new Weather(1, "25.5", "London", "1000 мм", "10 м/с", "75%");
        when(weatherRepository.findWeatherByTemperature(temperature))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findByTemperature(temperature).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findWeatherByTemperature(temperature);
    }

    @Test
    public void findByTemperature_invalidTemperature_throwStringNotStartWithDigitException() {
        String temperature = "25.5";
        when(weatherRepository.findWeatherByTemperature(temperature))
                .thenReturn(Optional.empty());

        assertThrows(WeatherWithTemperatureNotFoundException.class,
                () -> weatherService.findByTemperature(temperature));
        verify(weatherRepository, times(1))
                .findWeatherByTemperature(temperature);
    }

    @Test
    public void findByPressure_validPressure_returnWeather() {
        String pressure = "1000 мм";
        Weather expectedWeather = new Weather(1, "25.5", "London", "1000 мм", "10 м/с", "75%");
        when(weatherRepository.findWeatherByPressure(pressure))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findByPressure(pressure).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findWeatherByPressure(pressure);
    }

    @Test
    public void findByPressure_invalidPressure_throwStringNotStartWithDigitException() {
        String pressure = "1000 мм";
        when(weatherRepository.findWeatherByPressure(pressure))
                .thenReturn(Optional.empty());

        assertThrows(WeatherWithPressureNotFoundException.class,
                () -> weatherService.findByPressure(pressure));
        verify(weatherRepository, times(1))
                .findWeatherByPressure(pressure);
    }


    @Test
    public void findBySpeed_validSpeed_returnWeather() {
        String speed = "10 м/с";
        Weather expectedWeather = new Weather(1, "25.5", "London", "1000 мм", "10 м/с", "75%");
        when(weatherRepository.findWeatherBySpeed(speed))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findBySpeed(speed).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findWeatherBySpeed(speed);
    }

    @Test
    public void findBySpeed_invalidSpeed_throwStringNotStartWithDigitException() {
        String speed = "10 м/с";
        when(weatherRepository.findWeatherBySpeed(speed))
                .thenReturn(Optional.empty());

        assertThrows(WeatherWithSpeedNotFoundException.class,
                () -> weatherService.findBySpeed(speed));
        verify(weatherRepository, times(1))
                .findWeatherBySpeed(speed);
    }

    @Test
    public void findByHumidity_validHumidity_returnWeather() {
        String humidity = "75%";
        Weather expectedWeather = new Weather(1, "25.5", "London", "1000 мм", "10 м/с", "75%");
        when(weatherRepository.findWeatherByHumidity(humidity))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findByHumidity(humidity).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findWeatherByHumidity(humidity);
    }

    @Test
    public void findByHumidity_invalidHumidity_throwStringNotStartWithDigitException() {
        String speed = "75%";
        when(weatherRepository.findWeatherByHumidity(speed))
                .thenReturn(Optional.empty());

        assertThrows(WeatherWithHumidityNotFoundException.class,
                () -> weatherService.findByHumidity(speed));
        verify(weatherRepository, times(1))
                .findWeatherByHumidity(speed);
    }


    @Test
    public void deleteWeatherById_whenIdExists_shouldDeleteWeather() {
        Weather weather = new Weather();
        weatherRepository.save(weather);
        long id = weather.getId();

        weatherService.deleteWeatherById(id);

        assertThat(weatherRepository.findById(id)).isEmpty();
    }

    @Test
    public void deleteWeatherById_whenIdDoesNotExist_shouldNotThrowException() {
        long id = 1L;

        Throwable exception = catchThrowable(() -> weatherService.deleteWeatherById(id));

        assertThat(exception).isNull();
    }
}
