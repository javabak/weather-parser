package com.laborotoryproject.weather.parser.repository;

import com.laborotoryproject.weather.parser.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findWeatherByCityName(String citiName);
    Optional<List<Weather>> findWeatherByTemperature(String temperature);
    Optional<List<Weather>> findWeatherByPressure(String pressure);
    Optional<List<Weather>> findWeatherBySpeed(String speed);
    Optional<List<Weather>> findWeatherByHumidity(String humidity);

}
