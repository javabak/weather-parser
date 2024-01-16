package com.laborotoryproject.weather.parser.controller;

import com.laborotoryproject.weather.parser.dto.WeatherDto;
import com.laborotoryproject.weather.parser.factory.WeatherDtoFactory;
import com.laborotoryproject.weather.parser.service.WeatherService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/v1/")
@OpenAPIDefinition(
        info = @Info(
                title = "Weather API",
                description = "controller for working with weather parser",
                summary = "this api getting weather by the some parameters",
                version = "v1"
        )
)
public class WeatherController {

    WeatherService weatherService;
    WeatherDtoFactory weatherDtoFactory;

    static final String GET_WEATHER_BY_CITY_NAME = "/weather/cityName/{cityName}";
    static final String GET_WEATHER_BY_ID = "/weather/{id}";
    static final String GET_ALL_WEATHERS_BY_TEMPERATURE = "/getAllWeathersByTemp/{temp}";
    static final String GET_ALL_WEATHERS_BY_PRESSURE = "/getAllWeathersByPressure/{pressure}";
    static final String GET_ALL_WEATHERS_BY_SPEED = "/getAllWeathersBySpeed/{speed}";
    static final String GET_WEATHER_BY_SPEED = "/getWeatherBySpeed/{speed}";
    static final String GET_WEATHER_BY_PRESSURE = "/getWeatherByPressure/{pressure}";
    static final String GET_WEATHER_BY_TEMPERATURE = "/getWeatherByTemperature/{temperature}";
    static final String DELETE_WEATHER_BY_ID = "/deleteWeatherById/{id}";


    @Autowired
    public WeatherController(WeatherService weatherService,
                             WeatherDtoFactory weatherDtoFactory) {
        this.weatherService = weatherService;
        this.weatherDtoFactory = weatherDtoFactory;
    }


    @GetMapping(GET_WEATHER_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get weather by id")
    public ResponseEntity<WeatherDto> getWeatherById(@PathVariable int id) {
        log.info("getting weather with id".concat(String.valueOf(id)));
        return ResponseEntity.ok(weatherDtoFactory.makeDto(weatherService.findById(id)));
    }

    @GetMapping(GET_WEATHER_BY_CITY_NAME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get weather by city_name")
    public ResponseEntity<WeatherDto> getWeatherByCityName(@PathVariable String cityName) {
        log.info("getting city ".concat(cityName));
        return ResponseEntity.ok(weatherDtoFactory.makeDto(weatherService.findWeatherByCityName(cityName)));
    }


    @GetMapping(GET_ALL_WEATHERS_BY_TEMPERATURE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get all possible the weathers"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get all weathers by temperature")
    public ResponseEntity<List<WeatherDto>> getAllWeathersByTemperature(@PathVariable String temp) {
        log.info("getting all weathers with temperature".concat(temp));
        return ResponseEntity.ok(weatherService.findByTemperature(temp).stream().map(weatherDtoFactory::makeDto).toList());
    }

    @GetMapping(GET_ALL_WEATHERS_BY_PRESSURE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get all possible weathers"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get all weathers by pressure")
    public ResponseEntity<List<WeatherDto>> getAllWeathersByPressure(@PathVariable String pressure) {
        log.info("getting all weathers with pressure".concat(pressure));
        return ResponseEntity.ok(weatherService.findByPressure(pressure).stream().map(weatherDtoFactory::makeDto).toList());
    }

    @GetMapping(GET_ALL_WEATHERS_BY_SPEED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get all possible weathers"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get all weathers by speed")
    public ResponseEntity<List<WeatherDto>> getAllWeathersBySpeed(@PathVariable String speed) {
        log.info("getting all weathers with speed".concat(speed));
        return ResponseEntity.ok(weatherService.findBySpeed(speed).stream().map(weatherDtoFactory::makeDto).toList());
    }


    @GetMapping(GET_WEATHER_BY_SPEED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get weather by speed")
    public ResponseEntity<WeatherDto> getWeatherBySpeed(@PathVariable String speed) {
        log.info("getting weather with speed".concat(speed));
        return ResponseEntity.ok(weatherDtoFactory.makeDto(weatherService.findBySpeed(speed).stream().findFirst().get()));
    }


    @GetMapping(value = GET_WEATHER_BY_PRESSURE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get weather by pressure")
    public ResponseEntity<WeatherDto> getWeatherByPressure(@PathVariable String pressure) {
        log.info("getting weather with pressure".concat(pressure));
        return ResponseEntity.ok(weatherDtoFactory.makeDto(weatherService.findByPressure(pressure).stream().findFirst().get()));
    }

    @GetMapping(GET_WEATHER_BY_TEMPERATURE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully get the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "get weather by temperature")
    public ResponseEntity<WeatherDto> getWeatherByTemp(@PathVariable String temperature) {
        log.info("getting weather with temperature".concat(temperature));
        return ResponseEntity.ok(weatherDtoFactory.makeDto(weatherService.findByTemperature(temperature).stream().findFirst().get()));
    }


    @DeleteMapping(DELETE_WEATHER_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully delete the weather"),
            @ApiResponse(responseCode = "404", description = "resource not found"),
    })
    @Operation(description = "delete weather by id")
    public ResponseEntity<?> deleteWeatherById(@PathVariable long id) {
        log.info("deleting weather with id".concat(String.valueOf(id)));
        weatherService.deleteWeatherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
