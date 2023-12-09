package com.laborotoryproject.weather.parser.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        bearerFormat = "basic authorization"
)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf -> {
                }))
                .httpBasic((httpBasic) -> {
                })
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
                                .permitAll()
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
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User
                .builder()
                .username("user")
                .password(passwordEncoder().encode("pass"))
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}