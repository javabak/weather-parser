package com.laborotoryproject.weatherparser.dao;

import com.laborotoryproject.weather.parser.dao.MyUserDao;
import com.laborotoryproject.weather.parser.entity.MyUser;
import com.laborotoryproject.weather.parser.mapper.MyUserEntityRowMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyUserDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private MyUserDao userDao;

    @Test
    void testFindByLogin() {
        String login = "testUser";
        MyUser expectedUser = MyUser.builder().login(login).build();

        when(namedParameterJdbcTemplate.queryForObject(
                eq("SELECT * FROM user WHERE login = :login"),
                any(MapSqlParameterSource.class),
                any(MyUserEntityRowMapper.class)
        )).thenReturn(expectedUser);

        MyUser actualUser = userDao.findByLogin(login);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);

        verify(namedParameterJdbcTemplate, times(1))
                .queryForObject(eq("SELECT * FROM user WHERE login = :login"),
                        any(MapSqlParameterSource.class),
                        any(MyUserEntityRowMapper.class));
    }
}
