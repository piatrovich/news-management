package com.epam.lab.news.database.jdbc.pool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
@PropertySource("classpath:pool/jdbc.properties")
public class ConnectionPool {
    private static Logger logger = Logger.getLogger("errors");

    @Autowired
    PropertySourcesPlaceholderConfigurer configurer;

    private @Value("${jdbc.driver}") String driver;
    private @Value("${jdbc.url}") String url;
    private @Value("${jdbc.size}") Integer size;

    private BlockingQueue<Connection> pool;

    /* If this class put into another spring application the pool
     would be own for both applications!?
     Think about double-check idiom, it should be faster solution
    private ConnectionPool instance;
    public synchronized ConnectionPool getInstance() {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    } */

    @PostConstruct
    private void init() {
        pool = new ArrayBlockingQueue<Connection>(size);
        try {
            Class.forName(driver);
            for (int i = 0; i < size; ++i) {
                pool.add(DriverManager.getConnection(url));
            }
        } catch (ClassNotFoundException e) {
            logger.error(" ", e);
        } catch (SQLException e) {
            logger.error("Error when trying to create a new connection", e);
        }

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }
        } catch (InterruptedException e) {
            logger.error("Can't take connection from pool.", e);
        } catch (SQLException e) {
            logger.error("Getting connection from DriverManager failed.", e);
        }
        size();
        return connection;
    }

    public void returnConnection(Connection connection) {
        if(connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                logger.error("Can't put connection to pool.", e);
            }
        }
    }

    @PreDestroy
    private void destroy() {
        while (!pool.isEmpty()) {
            try {
                pool.take().close();
            } catch (SQLException e) {
                logger.error("Closing connection failed.", e);
            } catch (InterruptedException e) {
                logger.error("Taking connection from pool failed. Pool destroying failed.", e);
            }
        }
    }

    public int size() {
        return pool != null ? pool.size() : 0;
    }

}
