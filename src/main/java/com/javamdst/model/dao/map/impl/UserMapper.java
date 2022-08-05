package com.javamdst.model.dao.map.impl;


import com.javamdst.domain.User;
import com.javamdst.model.dao.map.GenericMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements GenericMapper<User> {

    @Override
    public User rowMapper(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        boolean enabled = resultSet.getBoolean("enabled");

        return new User(id, username, password, enabled);
    }
}
