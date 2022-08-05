package com.javamdst.model.dao.impl;


import com.javamdst.domain.User;
import com.javamdst.model.config.DataConnection;
import com.javamdst.model.dao.api.GenericDao;
import com.javamdst.model.dao.map.impl.UserMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements GenericDao<User> {

    private Connection connection;

    private UserMapper userMapper = new UserMapper();

    private static final String INSERT_USER = "INSERT INTO users(username, password) VALUES (? , ?)";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ? ";
    private static final String UPDATE_USER = "UPDATE users SET username = ? , password = ?, enabled = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ? ";


    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int saveItem(User item) throws SQLException {
// we use this field and return it ,in order to see if the insert in the DB was successful or not
        int insertedRecord = 0;

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

        preparedStatement.setString(1, item.getUsername());
        preparedStatement.setString(2, item.getPassword());

        insertedRecord = preparedStatement.executeUpdate();

//we close the connection resource so we can minimise CPU -> increse speed -> better performance
        DataConnection.closeStatement(preparedStatement);

        return insertedRecord;
    }

    @Override
    public User findById(int id) throws SQLException {

        User user = new User();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            user = userMapper.rowMapper(resultSet);
        }

        DataConnection.closeStatement(preparedStatement);
        DataConnection.closeResultSet(resultSet);

        //we don't want to return null so we can reduse the posibility of nullPoinerExeption
        //cause if the new User is empty , ican check if the id of it is 0

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<User>();

        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new UserMapper().rowMapper(resultSet);
            users.add(user);
        }

        //close resourses
        DataConnection.closeResultSet(resultSet);
        DataConnection.closeStatement(statement);

        return users;
    }

    @Override
    public int updateItem(User item) throws SQLException {
        int updatedRecord = 0;

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);


        preparedStatement.setString(1, item.getUsername());
        preparedStatement.setString(2, item.getPassword());
        preparedStatement.setBoolean(3, item.isEnabled());
        preparedStatement.setInt(4, item.getId());

        updatedRecord = preparedStatement.executeUpdate();

        DataConnection.closeStatement(preparedStatement);

        return updatedRecord;
    }

    @Override
    public int deleteById(int id) throws SQLException {

        int deletedRecord = 0;

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);

        preparedStatement.setInt(1, id);

        deletedRecord = preparedStatement.executeUpdate();

        DataConnection.closeStatement(preparedStatement);

        return deletedRecord;
    }
}
