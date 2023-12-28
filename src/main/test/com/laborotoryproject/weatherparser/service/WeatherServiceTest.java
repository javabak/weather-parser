package com.laborotoryproject.weatherparser.service;


import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherWithIdNotFoundException;
import com.laborotoryproject.weather.parser.exception.request_exceptions.WeatherWithTemperatureNotFoundException;
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
        String temperature = "25.5 C";
        Weather expectedWeather = new Weather(1, "London", "25.5 C", "1000 hPa", "10 m/s", "75%");
        when(weatherRepository.findWeatherByTemperature(temperature))
                .thenReturn(Optional.of(Collections.singletonList(expectedWeather)));

        Weather actualWeather = weatherService.findByTemperature(temperature).stream().findFirst().get();

        assertEquals(expectedWeather, actualWeather);
        verify(weatherRepository, times(1))
                .findWeatherByTemperature(temperature);
    }

    @Test
    public void findByTemperature_invalidTemperature_throwStringNotStartWithDigitException() {
        String temperature = "25C";
        when(weatherRepository.findWeatherByTemperature(temperature))
                .thenReturn(Optional.empty());

        assertThrows(WeatherWithTemperatureNotFoundException.class,
                () -> weatherService.findByTemperature(temperature));
        verify(weatherRepository, times(1))
                .findWeatherByTemperature(temperature);
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
