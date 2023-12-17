package com.laborotoryproject.weather.parser.dao;

import com.laborotoryproject.weather.parser.entity.MyUser;
import com.laborotoryproject.weather.parser.mapper.MyUserEntityRowMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class MyUserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public MyUser findByLogin(String login) {
        String sql = "SELECT * FROM user WHERE login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource("login", login);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new MyUserEntityRowMapper());
    }
}
