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

    @GetMapping("/weather/id")
    public String getWeatherById(@RequestParam long id, Model model) {
        model.addAttribute("weather",
                weatherDtoFactory.makeDto(weatherService.findById(id)));
        return "weather-by-id";
    }

    @GetMapping("/getWeathersByTemp")
    public String getWeathersByTemp(@RequestParam String temperature, Model model) {
        model.addAttribute("weathersByTemp",
                weatherService.findByTemperature(temperature).stream().map(weatherDtoFactory::makeDto));
        return "weathersByTemp";
    }

    @GetMapping("/getWeathersByPressure")
    public String getWeathersByPressure(@RequestParam String pressure, Model model) {
        model.addAttribute("weathersByPressure",
                weatherService.findByPressure(pressure).stream().map(weatherDtoFactory::makeDto));
        return "weathersByPressure";
    }

    @GetMapping("/getWeathersBySpeed")
    public String getWeathersBySpeed(@RequestParam String speed, Model model) {
        model.addAttribute("weathersBySpeed",
                weatherService.findBySpeed(speed).stream().map(weatherDtoFactory::makeDto));
        return "weathersBySpeed";
    }

    @GetMapping("/getWeathersByHumidity")
    public String getWeathersByHumidity(@RequestParam String humidity, Model model) {
        model.addAttribute("weathersByHumidity"
                , weatherService.findByHumidity(humidity).stream().map(weatherDtoFactory::makeDto));
        return "weathersByHumidity";
    }

    @GetMapping("/getWeatherByTemperature")
    public String getWeatherByTemperature(@RequestParam String temperature, Model model) {
        model.addAttribute("weatherByTemperature",
                weatherDtoFactory.makeDto(weatherService.findByTemperature(temperature).stream().findAny().get()));
        return "weatherByTemperature";
    }

    @GetMapping("/getWeatherByPressure")
    public String getWeatherByPressure(@RequestParam String pressure, Model model) {
        model.addAttribute("weatherByPressure",
                weatherDtoFactory.makeDto(weatherService.findByPressure(pressure).stream().findAny().get()));
        return "weatherByPressure";
    }

    @GetMapping("/getWeatherBySpeed")
    public String getWeatherBySpeed(@RequestParam String speed, Model model) {
        model.addAttribute("weatherBySpeed",
                weatherDtoFactory.makeDto(weatherService.findBySpeed(speed).stream().findAny().get()));
        return "weatherBySpeed";
    }

    @GetMapping("/getWeatherByHumidity")
    public String getWeatherByHumidity(@RequestParam String humidity, Model model) {
        model.addAttribute("weatherByHumidity",
                weatherDtoFactory.makeDto(weatherService.findByHumidity(humidity).stream().findAny().get()));
        return "weatherByHumidity";
    }
}
