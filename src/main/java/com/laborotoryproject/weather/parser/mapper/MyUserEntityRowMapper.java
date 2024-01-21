package com.laborotoryproject.weather.parser.mapper;

import com.laborotoryproject.weather.parser.entity.MyUser;
import com.laborotoryproject.weather.parser.entity.enums.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUserEntityRowMapper implements RowMapper<MyUser> {

    @Nullable
    @Override
    public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MyUser
                .builder()
                .id(rs.getInt("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role")))
                .position(rs.getString("position"))
                .build();
    }
}
