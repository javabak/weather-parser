package com.laborotoryproject.weather.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;


@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class WeatherDto {

    @NotNull
    @Nullable
    @JsonProperty("temperature")
    String temperature;

    @NotNull
    @Nullable
    @JsonProperty("city_name")
    String cityName;

    @NotNull
    @Nullable
    @JsonProperty("pressure")
    String pressure;

    @NotNull
    @Nullable
    @JsonProperty("humidity")
    String humidity;

    @NotNull
    @Nullable
    @JsonProperty("speed")
    String speed;
}
