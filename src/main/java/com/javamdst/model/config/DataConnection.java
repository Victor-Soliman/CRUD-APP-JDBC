package com.javamdst.model.config;

import java.sql.*;

public class DataConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/crud_jdbc";
        String user = "root";
        String password = "HelloMySql1!";

        return DriverManager.getConnection(url, user, password);
    }

    public static void closeStatement(final Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(final ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
