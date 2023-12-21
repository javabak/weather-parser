package com.laborotoryproject.weather.parser.util.db;

import com.laborotoryproject.weather.parser.entity.Weather;
import com.laborotoryproject.weather.parser.service.WeatherService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InsertWeatherDataIntoDataBase {

    private static final String URL = "https://pogoda.mail.ru/country/russia/";

    WeatherService weatherService;
    Document document;

    public InsertWeatherDataIntoDataBase(WeatherService weatherService) throws IOException {
        this.weatherService = weatherService;
        document = Jsoup.connect(URL).get();
    }

    @PostConstruct
    @Scheduled(cron = "0 0 12 * * ?")
    private void getWeather() {
        Elements elements = document.getElementsByAttributeValue("class", "city-list__item");

        elements.forEach(element -> {
            if (!element.getElementsByClass("city-list__val city-list__val-text").text().equals("")) {
                String city = element.getElementsByClass("city-list__val city-list__val-text").text();
                String temp = element.getElementsByClass("city-list__val city-list__val-temperature").text();
                String humidity = element.getElementsByClass("city-list__val city-list__val-humidity").text();
                String pressure = element.getElementsByClass("city-list__val city-list__val-pressure").text();
                String speed = element.getElementsByClass("city-list__val city-list__val-wind").text() + " м/с";

                extracted(city, temp, humidity, pressure, speed);
            }
        });
    }

    private void extracted(String city, String temp, String humidity, String pressure, String speed) {
        if (temp.startsWith("+")) {
            Weather weather = new Weather();
            weather.setCityName(city);
            weather.setTemperature(temp.substring(0, temp.length() - 1).replace("+", ""));
            weather.setHumidity(humidity);
            weather.setPressure(pressure);
            weather.setSpeed(speed);
            weatherService.save(weather);
        } else {
            Weather weather = new Weather();
            weather.setCityName(city);
            weather.setTemperature(temp.substring(0, temp.length() - 1));
            weather.setHumidity(humidity);
            weather.setPressure(pressure);
            weather.setSpeed(speed);
            weatherService.save(weather);
        }
    }
}
