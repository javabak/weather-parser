package com.laborotoryproject.weatherparser.controller;

import com.laborotoryproject.weather.parser.WeatherParserApplication;
import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = WeatherParserApplication.class)
public class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherRepository weatherRepository;


    @Test
    public void testGetBookById() throws Exception {
        long id = 1;
        Weather weather = new Weather(1, "12", "", "", "", "");
        when(weatherRepository.findById(id)).thenReturn(Optional.of(weather));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testGetBookById_WithDoesNotExistId() throws Exception {
        long id = 1;
        Weather weather = new Weather(1, "", "", "", "", "");

        when(weatherRepository.findById(id)).thenReturn(Optional.of(weather));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testGetBookById_WithNoValidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/{id}",
                                "invalid-id")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetWeatherByCityName() throws Exception {
        String cityName = "Москва";
        Weather weather = new Weather(1, "", cityName, "", "", "");
        when(weatherRepository.findWeatherByCityName(cityName)).thenReturn(Optional.of(weather));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/cityName/{cityName}", cityName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());
        verify(weatherRepository, times(1)).findWeatherByCityName(cityName);

    }

    @Test
    public void testGetWeatherByCityName_WithDoesNotExistCityName() throws Exception {
        String cityName = "Москва";
        Weather weather = new Weather(1, "", cityName, "", "", "");

        when(weatherRepository.findWeatherByCityName(cityName)).thenReturn(Optional.of(weather));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/cityName/{cityName}", "city")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findWeatherByCityName(anyString());
    }

    @Test
    public void testGetWeatherByCityName_WithNoValidCityName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/weather/cityName/{cityName}",
                                "1")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void testGetAllWeathersByTemperature() throws Exception {
        String temperature = "25.5";
        Weather weather1 = new Weather(1, temperature, "", "", "", "");
        Weather weather2 = new Weather(2, temperature, "", "", "", "");
        when(weatherRepository.findWeatherByTemperature(temperature)).thenReturn(Optional.of(List.of(weather1, weather2)));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getAllWeathersByTemp/{temp}", temperature)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findWeatherByTemperature(anyString());
    }

    @Test
    public void testGetAllWeathersByTemperature_WithDoesNotExistTemperature() throws Exception {
        String temperature = "25.5";
        Weather weather1 = new Weather(1, temperature, "", "", "", "");
        Weather weather2 = new Weather(2, temperature, "", "", "", "");

        when(weatherRepository.findWeatherByTemperature(temperature)).thenReturn(Optional.of(List.of(weather1, weather2)));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getAllWeathersByTemp/{temp}", "1231")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findWeatherByTemperature(anyString());
    }

    @Test
    public void testGetAllWeathersByTemperature_WithNoValidTemperature() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getAllWeathersByTemp/{temp}",
                                "asd")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getAllWeathersByPressure() throws Exception {
        String pressure = "1000 мм";
        Weather weather1 = new Weather(1, "", "", pressure, "", "");
        Weather weather2 = new Weather(2, "", "", pressure, "", "");
        when(weatherRepository.findWeatherByPressure(pressure)).thenReturn(Optional.of(List.of(weather1, weather2)));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getWeatherByPressure/{pressure}", pressure)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findWeatherByPressure(anyString());
    }

    @Test
    public void testGetAllWeathersByPressure_WithDoesNotExistPressure() throws Exception {
        String pressure = "1000 мм";
        Weather weather1 = new Weather(1, "", "", "", pressure, "");
        Weather weather2 = new Weather(2, "", "", "", pressure, "");

        when(weatherRepository.findWeatherByPressure(pressure)).thenReturn(Optional.of(List.of(weather1, weather2)));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getWeatherByPressure/{pressure}", "111111")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id").doesNotExist());

        verify(weatherRepository, times(1)).findWeatherByPressure(anyString());
    }

    @Test
    public void testGetAllWeathersByPressure_WithNoValidPressure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getWeatherByPressure/{pressure}",
                                "asd")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
