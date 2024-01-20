package com.laborotoryproject.weatherparser.service;


import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherNotFound;
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

        assertThrows(WeatherNotFound.class, () -> weatherService.findById(1L));
        verify(weatherRepository, times(1)).findById(1L);
    }

    @Test
    public void findByTemperature_validTemperature_returnWeather() {
        String temperature = "25";
        Weather expectedWeather = new Weather(1, temperature, "London", "1000 мм", "65", "75 мс");
        when(weatherRepository.findAllWeathersByTemperature(temperature))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findAllWeathersByTemperature(temperature).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findAllWeathersByTemperature(temperature);
    }

    @Test
    public void findByTemperature_invalidTemperature_throwStringNotStartWithDigitException() {
        String temperature = "25";
        when(weatherRepository.findAllWeathersByTemperature(temperature))
                .thenReturn(Optional.empty());

        assertThrows(WeatherNotFound.class,
                () -> weatherService.findAllWeathersByTemperature(temperature));
        verify(weatherRepository, times(1))
                .findAllWeathersByTemperature(temperature);
    }

    @Test
    public void findByPressure_validPressure_returnWeather() {
        String pressure = "1000 мм";
        Weather expectedWeather = new Weather(1, "12", "London", pressure, "65", "75 мс");
        when(weatherRepository.findAllWeathersByPressure(pressure))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findAllWeathersByPressure(pressure).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findAllWeathersByPressure(pressure);
    }

    @Test
    public void findByPressure_invalidPressure_throwStringNotStartWithDigitException() {
        String pressure = "1000 мм";
        when(weatherRepository.findAllWeathersByPressure(pressure))
                .thenReturn(Optional.empty());

        assertThrows(WeatherNotFound.class,
                () -> weatherService.findAllWeathersByPressure(pressure));
        verify(weatherRepository, times(1))
                .findAllWeathersByPressure(pressure);
    }


    @Test
    public void findBySpeed_validSpeed_returnWeather() {
        String speed = "10 мс";
        Weather expectedWeather = new Weather(1, "25", "London", "1000 мм", "65", speed);
        when(weatherRepository.findAllWeathersBySpeed(speed))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findAllWeathersBySpeed(speed).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findAllWeathersBySpeed(speed);
    }

    @Test
    public void findBySpeed_invalidSpeed_throwStringNotStartWithDigitException() {
        String speed = "10 мс";
        when(weatherRepository.findAllWeathersBySpeed(speed))
                .thenReturn(Optional.empty());

        assertThrows(WeatherNotFound.class,
                () -> weatherService.findAllWeathersBySpeed(speed));
        verify(weatherRepository, times(1))
                .findAllWeathersBySpeed(speed);
    }

    @Test
    public void findByHumidity_validHumidity_returnWeather() {
        String humidity = "65";
        Weather expectedWeather = new Weather(1, "12", "London", "1000 мм", humidity, "75 мс");
        when(weatherRepository.findAllWeathersByHumidity(humidity))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findAllWeathersByHumidity(humidity).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findAllWeathersByHumidity(humidity);
    }

    @Test
    public void findByHumidity_invalidHumidity_throwStringNotStartWithDigitException() {
        String humidity = "75";
        when(weatherRepository.findAllWeathersByHumidity(humidity))
                .thenReturn(Optional.empty());

        assertThrows(WeatherNotFound.class,
                () -> weatherService.findAllWeathersByHumidity(humidity));
        verify(weatherRepository, times(1))
                .findAllWeathersByHumidity(humidity);
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
