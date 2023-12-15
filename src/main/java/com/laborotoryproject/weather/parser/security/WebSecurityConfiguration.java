package com.laborotoryproject.weather.parser.security;

import com.laborotoryproject.weather.parser.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .csrf((csrf -> {
                }))
                .httpBasic((httpBasic) -> {
                })
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests((authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        "/weather/citiName/{citiName}", "/weather/{id}",
                                        "/getAllWeathersByTemp/{temp}", "/getAllWeathersByPressure/{pressure}",
                                        "/getAllWeathersBySpeed/{speed}", "/getWeatherBySpeed/{speed}",
                                        "/getWeatherByPressure/{pressure}", "/getWeatherByTemperature/{temperature}," +
                                                                            "/swagger-ui.html", "/swagger-ui/**",
                                        "/v1/api-docs", "/v2/api-docs", "/v3/api-docs"
                                )
                                .hasRole("ADMIN")
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