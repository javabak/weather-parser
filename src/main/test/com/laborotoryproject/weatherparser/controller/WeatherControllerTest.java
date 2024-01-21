package com.laborotoryproject.weatherparser.controller;

import com.laborotoryproject.weather.parser.WeatherParserApplication;
import com.laborotoryproject.weather.parser.dto.WeatherDto;
import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringNotStartWithDigitException;
import com.laborotoryproject.weather.parser.factory.WeatherDtoFactory;
import com.laborotoryproject.weather.parser.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = WeatherParserApplication.class)
public class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherService weatherService;

    @MockBean
    WeatherDtoFactory weatherDtoFactory;

    @Test
    public void testGetAllWeathersByPressure_validPressure() throws Exception {
        String pressure = "751 мм";
        List<Weather> mockWeather = List.of(
                Weather.builder().build(),
                Weather.builder().build()
        );
        List<WeatherDto> weathersDto = List.of(
                WeatherDto.builder().pressure(pressure).build(),
                WeatherDto.builder().pressure(pressure).build()
        );

        when(weatherService.findAllWeathersByPressure(pressure)).thenReturn(mockWeather);
        when(weatherDtoFactory.makeListDto(mockWeather)).thenReturn(weathersDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByPressure/{pressure}", pressure)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pressure").value(pressure))
                .andExpect(jsonPath("$[1].pressure").value(pressure));

        verify(weatherService, times(1)).findAllWeathersByPressure(pressure);
        verify(weatherDtoFactory, times(1)).makeListDto(mockWeather);
    }

    @Test
    public void testGetAllWeathersByPressure_invalidPressure() throws Exception {
        when(weatherService.findAllWeathersByPressure(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByPressure/{pressure}", "pressure"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByPressure("pressure");
    }

    @Test
    public void testGetAllWeathersByTemperature_validTemperature() throws Exception {
        String temp = "12";
        List<Weather> mockWeather = List.of(
                Weather.builder().build(),
                Weather.builder().build()
        );
        List<WeatherDto> weathersDto = List.of(
                WeatherDto.builder().temperature(temp).build(),
                WeatherDto.builder().temperature(temp).build()
        );

        when(weatherService.findAllWeathersByTemperature(temp)).thenReturn(mockWeather);
        when(weatherDtoFactory.makeListDto(mockWeather)).thenReturn(weathersDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByTemp/{temp}", temp)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].temperature").value(temp))
                .andExpect(jsonPath("$[1].temperature").value(temp));

        verify(weatherService, times(1)).findAllWeathersByTemperature(temp);
        verify(weatherDtoFactory, times(1)).makeListDto(mockWeather);
    }

    @Test
    public void testGetAllWeathersByTemperature_invalidTemperature() throws Exception {
        when(weatherService.findAllWeathersByTemperature(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByTemp/{temp}", "temp"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByTemperature("temp");
    }

    @Test
    public void testGetAllWeathersByHumidity_validHumidity() throws Exception {
        String humidity = "76";
        List<Weather> mockWeather = List.of(
                Weather.builder().build(),
                Weather.builder().build()
        );
        List<WeatherDto> weathersDto = List.of(
                WeatherDto.builder().humidity(humidity).build(),
                WeatherDto.builder().humidity(humidity).build()
        );

        when(weatherService.findAllWeathersByHumidity(humidity)).thenReturn(mockWeather);
        when(weatherDtoFactory.makeListDto(mockWeather)).thenReturn(weathersDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByHumidity/{humidity}", humidity)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].humidity").value(humidity))
                .andExpect(jsonPath("$[1].humidity").value(humidity));

        verify(weatherService, times(1)).findAllWeathersByHumidity(humidity);
        verify(weatherDtoFactory, times(1)).makeListDto(mockWeather);
    }

    @Test
    public void testGetAllWeathersByHumidity_invalidHumidity() throws Exception {
        when(weatherService.findAllWeathersByHumidity(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersByHumidity/{humidity}", "humidity"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByHumidity("humidity");
    }

    @Test
    public void testGetAllWeathersBySpeed_validSpeed() throws Exception {
        String speed = "50 мс";
        List<Weather> mockWeather = List.of(
                Weather.builder().build(),
                Weather.builder().build()
        );
        List<WeatherDto> weathersDto = List.of(
                WeatherDto.builder().speed(speed).build(),
                WeatherDto.builder().speed(speed).build()
        );

        when(weatherService.findAllWeathersBySpeed(speed)).thenReturn(mockWeather);
        when(weatherDtoFactory.makeListDto(mockWeather)).thenReturn(weathersDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersBySpeed/{speed}", speed)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].speed").value(speed))
                .andExpect(jsonPath("$[1].speed").value(speed));

        verify(weatherService, times(1)).findAllWeathersBySpeed(speed);
        verify(weatherDtoFactory, times(1)).makeListDto(mockWeather);
    }

    @Test
    public void testGetAllWeathersBySpeed_invalidSpeed() throws Exception {
        when(weatherService.findAllWeathersBySpeed(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getAllWeathersBySpeed/{speed}", "speed"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersBySpeed("speed");
    }

    @Test
    public void testGetWeatherByHumidity_validHumidity() throws Exception {
        String humidity = "76";
        Weather mockWeather = new Weather();
        WeatherDto mockWeatherDto = WeatherDto.builder().humidity(humidity).build();

        when(weatherService.findAllWeathersByHumidity(humidity)).thenReturn(Collections.singletonList(mockWeather));
        when(weatherDtoFactory.makeDto(any(Weather.class))).thenReturn(mockWeatherDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByHumidity/{humidity}", humidity)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.humidity").value(humidity));

        verify(weatherService, times(1)).findAllWeathersByHumidity(humidity);
        verify(weatherDtoFactory, times(1)).makeDto(mockWeather);
    }

    @Test
    public void testGetWeatherByHumidity_invalidHumidity() throws Exception {
        when(weatherService.findAllWeathersByHumidity(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByHumidity/{humidity}", "humidity"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByHumidity("humidity");
    }

    @Test
    public void testGetWeatherByTemperature_validTemperature() throws Exception {
        String temperature = "0";
        Weather mockWeather = new Weather();
        WeatherDto mockWeatherDto = WeatherDto.builder().temperature(temperature).build();

        when(weatherService.findAllWeathersByTemperature(temperature)).thenReturn(Collections.singletonList(mockWeather));
        when(weatherDtoFactory.makeDto(any(Weather.class))).thenReturn(mockWeatherDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByTemperature/{temperature}", temperature)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(temperature));

        verify(weatherService, times(1)).findAllWeathersByTemperature(temperature);
        verify(weatherDtoFactory, times(1)).makeDto(mockWeather);
    }

    @Test
    public void testGetWeatherByTemperature_invalidTemperature() throws Exception {
        when(weatherService.findAllWeathersByTemperature(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByTemperature/{temperature}", "temp"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByTemperature("temp");
    }

    @Test
    public void getWeatherByPressure_validPressure() throws Exception {
        String pressure = "751 мм";
        Weather mockWeather = new Weather();
        WeatherDto mockWeatherDto = WeatherDto.builder().pressure(pressure).build();

        when(weatherService.findAllWeathersByPressure(pressure)).thenReturn(Collections.singletonList(mockWeather));
        when(weatherDtoFactory.makeDto(any(Weather.class))).thenReturn(mockWeatherDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByPressure/{pressure}", pressure)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pressure").value(pressure));

        verify(weatherService, times(1)).findAllWeathersByPressure(pressure);
        verify(weatherDtoFactory, times(1)).makeDto(mockWeather);
    }

    @Test
    public void testGetWeatherByPressure_invalidPressure() throws Exception {
        when(weatherService.findAllWeathersByPressure(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherByPressure/{pressure}", "pressure"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersByPressure("pressure");
    }


    @Test
    public void testGetWeatherBySpeed_validSpeed() throws Exception {
        String speed = "50 мс";
        Weather mockWeather = new Weather();
        WeatherDto mockWeatherDto = WeatherDto.builder().speed(speed).build();

        when(weatherService.findAllWeathersBySpeed(speed)).thenReturn(Collections.singletonList(mockWeather));
        when(weatherDtoFactory.makeDto(any(Weather.class))).thenReturn(mockWeatherDto);

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherBySpeed/{speed}", speed)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.speed").value(speed));

        verify(weatherService, times(1)).findAllWeathersBySpeed(speed);
        verify(weatherDtoFactory, times(1)).makeDto(mockWeather);
    }


    @Test
    public void testGetWeatherBySpeed_invalidSpeed() throws Exception {
        when(weatherService.findAllWeathersBySpeed(anyString()))
                .thenThrow(new StringNotStartWithDigitException("exception"));

        mockMvc.perform(get("http://localhost:8080/api/v1/getWeatherBySpeed/{speed}", "speed"))
                .andExpect(status().isBadRequest());
        verify(weatherService, times(1)).findAllWeathersBySpeed("speed");
    }

    @Test
    public void testDeleteWeatherById_validId() throws Exception {
        long weatherId = 1L;

        mockMvc.perform(delete("http://localhost:8080/api/v1/deleteWeatherById/{id}", weatherId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(weatherService, times(1)).deleteWeatherById(weatherId);
    }

    @Test
    public void testDeleteWeatherById_invalidId() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/v1/deleteWeatherById/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
