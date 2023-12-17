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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

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
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .httpBasic((httpBasic) -> {
                })
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
                                .hasAuthority(ADMIN.getGrantedAuthorities().toString())
                                .anyRequest()
                                .authenticated()
                ))
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .passwordParameter("password")
                                .usernameParameter("username")
                                .permitAll()
                                .defaultSuccessUrl("/success", true))
                .rememberMe((rememberMe) ->
                        rememberMe
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                                .key("somethingverysecured"))
                .logout((logout) ->
                        logout
                                .logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // if crsf is disabled
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("remember-me", "JSESSIONID")
                                .logoutSuccessUrl("/login")
                )
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