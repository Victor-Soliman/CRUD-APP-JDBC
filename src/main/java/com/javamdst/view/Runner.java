package com.javamdst.view;

import com.javamdst.domain.User;
import com.javamdst.model.config.DataConnection;
import com.javamdst.model.dao.impl.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws SQLException {
        // 1. get connection with the data base
        Connection connection = DataConnection.getConnection();


        // 2. create table in the data base using Satement

        String ceateUserTable = "CREATE TABLE IF NOT EXISTS users(\n" +
                "id int NOT NULL PRIMARY KEY auto_increment, \n" +
                "                username VARCHAR(50),\n" +
                "                password VARCHAR(50),\n" +
                "                enabled BOOLEAN DEFAULT TRUE );";

        Statement statement = connection.createStatement();
        statement.executeUpdate(ceateUserTable);

        // 3. create Users

        User userOne = new User("userOne", "passwordOne");
        User userTwo = new User("userTwo", "passwordTwo");
        User userThree = new User("userThree", "passwordThree");
        User userFour = new User("userFour", "passwordFour");

        // ====================== User Dao Operation ======================

        UserDao userDao = new UserDao(connection);

        // Create

        System.out.println("=============== CREATE ================");
        System.out.println("Inserted : " + userDao.saveItem(userOne));
        System.out.println("Inserted : " + userDao.saveItem(userTwo));
        System.out.println("Inserted : " + userDao.saveItem(userThree));
        System.out.println("Inserted : " + userDao.saveItem(userFour));

        System.out.println("================ READ =================");
        List<User> users = userDao.findAll();

        for (User userList : users) {
            System.out.println(userList);
        }

        System.out.println("============== Read By ID ==============");

        System.out.println("User Required : " + userDao.findById(2));

        System.out.println("================ UPDATE ==================");

        User userUpdated = new User(1,"parola","parola",true);

        System.out.println(userDao.updateItem(userUpdated));
        System.out.println(userUpdated);

        System.out.println("================= DELETE ==================");

        for (User allUsers : userDao.findAll()) {
            System.out.println(allUsers);
        }
        System.out.println("---");

        userDao.deleteById(3);

        for (User allUsersAfterDelete : userDao.findAll()) {
            System.out.println(allUsersAfterDelete);
        }


        // close connection
        DataConnection.closeConnection(connection);

    }
}
