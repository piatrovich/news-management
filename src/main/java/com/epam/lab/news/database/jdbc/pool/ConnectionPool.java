package com.epam.lab.news.database.jdbc.pool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@PropertySources({@PropertySource("classpath:pool/jdbc.properties"),
                  @PropertySource("classpath:logger.properties")})
public class ConnectionPool {
    /** Logger for errors */
    private static Logger logger = Logger.getLogger("errors");

    /** Wiring configurer for initializing fields */
    @Autowired
    PropertySourcesPlaceholderConfigurer configurer;

    /** Wiring environment for access to error messages */
    @Autowired
    Environment environment;

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

    /**
     * Initializes connection pool
     */
    @PostConstruct
    private void init() {
        pool = new ArrayBlockingQueue<Connection>(size);
        try {
            Class.forName(driver);
            for (int i = 0; i < size; ++i) {
                pool.add(DriverManager.getConnection(url));
            }
        } catch (ClassNotFoundException e) {
            logger.error(environment.getProperty("error.pool.class.not.found"), e);
        } catch (SQLException e) {
            logger.error(environment.getProperty("error.pool.create.connection"), e);
        }

    }

    /**
     * Returns database connection
     *
     * @return Connection object
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }
        } catch (InterruptedException e) {
            logger.error(environment.getProperty("error.pool.take.connection"), e);
        } catch (SQLException e) {
            logger.error(environment.getProperty("error.pool.create.connection"), e);
        }
        size();
        return connection;
    }

    /**
     * Return connection to pool
     *
     * @param connection Used connection
     */
    public void returnConnection(Connection connection) {
        if(connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                logger.error(environment.getProperty("error.pool.return.connection"), e);
            }
        }
    }

    /**
     * Closes all connections in pool
     */
    @PreDestroy
    private void destroy() {
        while (!pool.isEmpty()) {
            try {
                pool.take().close();
            } catch (SQLException e) {
                logger.error(environment.getProperty("error.pool.close.connection"), e);
            } catch (InterruptedException e) {
                logger.error(environment.getProperty("error.pool.take.connection"), e);
            }
        }
    }

    /**
     * Helpful method for checking number of connections
     *
     * @return Number of connections
     */
    public int size() {
        return pool != null ? pool.size() : 0;
    }

}
