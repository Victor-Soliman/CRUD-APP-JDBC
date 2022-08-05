package com.javamdst.model.dao.map;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericMapper<T> {
    T rowMapper(ResultSet resultSet) throws SQLException;
}
