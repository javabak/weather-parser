package com.laborotoryproject.weather.parser.repository;

import com.laborotoryproject.weather.parser.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findWeatherByCityName(String citiName);
    Optional<List<Weather>> findAllWeathersByTemperature(String temperature);
    Optional<List<Weather>> findAllWeathersByPressure(String pressure);
    Optional<List<Weather>> findAllWeathersBySpeed(String speed);
    Optional<List<Weather>> findAllWeathersByHumidity(String humidity);
}
