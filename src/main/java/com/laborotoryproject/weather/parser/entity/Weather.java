package com.laborotoryproject.weather.parser.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather")
@Tag(name = "weather entity")
public class Weather {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(updatable = false, nullable = false, length = 20)
    String temperature;

    @NonNull
    @JsonProperty("city_name")
    @Column(updatable = false, nullable = false, length = 20)
    String cityName;

    @NonNull
    @Column(updatable = false, nullable = false, length = 20)
    String pressure;

    @NonNull
    @Column(updatable = false, nullable = false, length = 20)
    String humidity;

    @NonNull
    @Column(updatable = false, nullable = false, length = 20)
    String speed;
}
