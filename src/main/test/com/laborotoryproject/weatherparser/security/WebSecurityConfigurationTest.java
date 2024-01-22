package com.laborotoryproject.weatherparser.security;

import com.laborotoryproject.weather.parser.WeatherParserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {WeatherParserApplication.class})
@AutoConfigureMockMvc
public class WebSecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUnauthenticatedAccessToProtectedEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testSuccessfulAuthentication() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("password"))
                .andExpect(authenticated());
    }

    @Test
    public void testFailedAuthentication() throws Exception {
        mockMvc.perform(formLogin().user("invalidUser").password("invalidPassword"))
                .andExpect(unauthenticated());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(logout())
                .andExpect(unauthenticated());
    }

}