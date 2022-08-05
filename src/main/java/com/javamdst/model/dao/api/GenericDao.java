package com.javamdst.model.dao.api;


import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    //CRUD

    //Create or Save
    int saveItem(T item) throws SQLException;

    //Read

    T findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    //Update
    int updateItem(T item) throws SQLException;

    //Delete

    int deleteById(int id) throws SQLException;


}
