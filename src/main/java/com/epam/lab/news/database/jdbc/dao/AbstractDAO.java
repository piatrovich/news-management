package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class AbstractDAO {
    /** Connection pool */
    protected ConnectionPool pool;

    public AbstractDAO(ConnectionPool pool){
        this.pool = pool;
    }

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;

}
