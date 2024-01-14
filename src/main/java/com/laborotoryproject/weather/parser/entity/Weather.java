package com.laborotoryproject.weather.parser.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@Builder
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

    @Column(updatable = false, length = 20)
    String temperature;

    @JsonProperty("city_name")
    @Column(updatable = false, length = 20)
    String cityName;

    @Column(updatable = false, length = 20)
    String pressure;

    @Column(updatable = false, length = 20)
    String humidity;

    @Column(updatable = false, length = 20)
    String speed;
}
