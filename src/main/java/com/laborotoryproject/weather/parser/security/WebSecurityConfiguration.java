package com.laborotoryproject.weather.parser.security;

import com.laborotoryproject.weather.parser.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.laborotoryproject.weather.parser.entity.Permissions.WEATHER_DELETE;
import static com.laborotoryproject.weather.parser.entity.Role.ADMIN;

@Configuration
@EnableWebSecurity
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        bearerFormat = "basic authorization"
)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf((AbstractHttpConfigurer::disable))
                .authenticationProvider(authenticationProvider())
                .httpBasic((httpBasic) -> {
                })
//                .authorizeHttpRequests((authorizeRequests -> {
//                    authorizeRequests
//                            .requestMatchers(HttpMethod.DELETE, "api/v1/deleteWeatherById/{id}")
//                            .hasAuthority(WEATHER_DELETE.getPermission());
//                }))
                .authorizeHttpRequests((authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                        "api/v1/**",
                        "/getWeatherByCityName", "/weather/id",
                        "/getWeathersByTemp", "/getWeathersByPressure",
                        "/getWeathersBySpeed", "/getWeatherBySpeed",
                        "/getWeatherByPressure", "/getWeathersByHumidity" +
                        "/getWeatherByHumidity", "/getWeatherByTemperature",
                        "/swagger-ui.html", "/swagger-ui/**",
                        "/v1/api-docs", "/v2/api-docs", "/v3/api-docs"
                                )
                                .hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, "api/v1/deleteWeatherById/{id}")
                                .hasAuthority(WEATHER_DELETE.getPermission())
                                .anyRequest()
                                .authenticated()
                ))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}