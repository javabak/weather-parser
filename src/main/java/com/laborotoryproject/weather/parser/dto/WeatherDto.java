package com.laborotoryproject.weather.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class WeatherDto {

    @NonNull
    @JsonProperty("temperature")
    String temperature;

    @NonNull
    @JsonProperty("city_name")
    String cityName;

    @NonNull
    @JsonProperty("pressure")
    String pressure;

    @NonNull
    @JsonProperty("humidity")
    String humidity;

    @NonNull
    @JsonProperty("speed")
    String speed;
}
