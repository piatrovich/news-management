package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.sql.*;

@PropertySource("classpath:logger.properties")
public abstract class AbstractDAO {
    /** Logger for dao layer */
    protected static Logger logger = Logger.getLogger("dao");

    /** Connection pool */
    @Autowired
    protected ConnectionPool pool;

    /**
     * Provides access to property sources
     */
    @Autowired
    protected Environment env;

    protected Statement statement;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;

}
