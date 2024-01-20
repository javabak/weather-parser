package com.laborotoryproject.weather.parser.controller;

import com.laborotoryproject.weather.parser.factory.WeatherDtoFactory;
import com.laborotoryproject.weather.parser.service.WeatherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ThymeleafController {

    WeatherService weatherService;
    WeatherDtoFactory weatherDtoFactory;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }


    @GetMapping("/weather/id")
    public String getWeatherById(@RequestParam long id, Model model) {
        model.addAttribute("weather",
                weatherDtoFactory.makeDto(weatherService.findById(id)));
        return "weather-by-id";
    }

    @GetMapping("/getWeatherByCityName")
    public String getWeatherByCityName(@RequestParam String cityName, Model model) {
        model.addAttribute("weatherByCityName",
                weatherDtoFactory.makeDto(weatherService.findWeatherByCityName(cityName)));
        return "weatherByCityName";
    }


    @GetMapping("/getWeathersByTemp")
    public String getWeathersByTemp(@RequestParam String temperature, Model model) {
        model.addAttribute("weathersByTemp",
                weatherService.findAllWeathersByTemperature(temperature).stream().map(weatherDtoFactory::makeDto));
        return "weathersByTemp";
    }

    @GetMapping("/getWeathersByPressure")
    public String getWeathersByPressure(@RequestParam String pressure, Model model) {
        model.addAttribute("weathersByPressure",
                weatherService.findAllWeathersByPressure(pressure).stream().map(weatherDtoFactory::makeDto));
        return "weathersByPressure";
    }

    @GetMapping("/getWeathersBySpeed")
    public String getWeathersBySpeed(@RequestParam String speed, Model model) {
        model.addAttribute("weathersBySpeed",
                weatherService.findAllWeathersBySpeed(speed).stream().map(weatherDtoFactory::makeDto));
        return "weathersBySpeed";
    }

    @GetMapping("/getWeathersByHumidity")
    public String getWeathersByHumidity(@RequestParam String humidity, Model model) {
        model.addAttribute("weathersByHumidity",
                weatherService.findAllWeathersByHumidity(humidity).stream().map(weatherDtoFactory::makeDto));
        return "weathersByHumidity";
    }

    @GetMapping("/getWeatherByTemperature")
    public String getWeatherByTemperature(@RequestParam String temperature, Model model) {
        model.addAttribute("weatherByTemperature",
                weatherDtoFactory.makeDto(weatherService.findAllWeathersByTemperature(temperature).stream().findFirst().get()));
        return "weatherByTemperature";
    }

    @GetMapping("/getWeatherByPressure")
    public String getWeatherByPressure(@RequestParam String pressure, Model model) {
        model.addAttribute("weatherByPressure",
                weatherDtoFactory.makeDto(weatherService.findAllWeathersByPressure(pressure).stream().findFirst().get()));
        return "weatherByPressure";
    }

    @GetMapping("/getWeatherBySpeed")
    public String getWeatherBySpeed(@RequestParam String speed, Model model) {
        model.addAttribute("weatherBySpeed",
                weatherDtoFactory.makeDto(weatherService.findAllWeathersBySpeed(speed).stream().findFirst().get()));
        return "weatherBySpeed";
    }

    @GetMapping("/getWeatherByHumidity")
    public String getWeatherByHumidity(@RequestParam String humidity, Model model) {
        model.addAttribute("weatherByHumidity",
                weatherDtoFactory.makeDto(weatherService.findAllWeathersByHumidity(humidity).stream().findFirst().get()));
        return "weatherByHumidity";
    }
}
