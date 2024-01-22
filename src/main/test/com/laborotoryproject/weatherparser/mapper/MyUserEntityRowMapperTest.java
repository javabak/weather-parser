package com.laborotoryproject.weatherparser.mapper;

import com.laborotoryproject.weather.parser.entity.MyUser;
import com.laborotoryproject.weather.parser.entity.enums.Role;
import com.laborotoryproject.weather.parser.mapper.MyUserEntityRowMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyUserEntityRowMapperTest {


    @Mock
    ResultSet resultSet;

    @InjectMocks
    private MyUserEntityRowMapper rowMapper;

    @Test
    void testMapRow() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("login")).thenReturn("testUser");
        when(resultSet.getString("password")).thenReturn("hashedPassword");
        when(resultSet.getString("role")).thenReturn("USER");
        when(resultSet.getString("position")).thenReturn("developer");

        MyUser actualUser = rowMapper.mapRow(resultSet, 1);

        assert actualUser != null;

        assertEquals(1, actualUser.getId());
        assertEquals("testUser", actualUser.getLogin());
        assertEquals("hashedPassword", actualUser.getPassword());
        assertEquals(Role.USER, actualUser.getRole());
        assertEquals("developer", actualUser.getPosition());
    }
}
