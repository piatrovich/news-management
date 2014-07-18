package com.epam.lab.news.database.jdbc.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Component
@PropertySource("classpath:pool/jdbc.properties")
public class ConnectionPool {
    private @Autowired PropertySourcesPlaceholderConfigurer configurer;

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
    private void init(){
        pool = new ArrayBlockingQueue<Connection>(size);
        try {
            Class.forName(driver);
            for(int i = 0; i < size; ++i){
                pool.add(DriverManager.getConnection(url));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException e) {
            System.out.println("Error when trying to create a new connection");
        }

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
            if (connection == null){
                connection = DriverManager.getConnection(url);
            }
        } catch (InterruptedException e) {

        } catch (SQLException e) {

        }
        return connection;
    }

    public void returnConnection(Connection connection){
        if(connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e){

            }
        }
    }

    @PreDestroy
    public void destroy(){
        while (!pool.isEmpty()) {
            try {
                pool.take().close();
            } catch (SQLException e) {

            } catch (InterruptedException e) {

            }
        }
    }

    public int size(){
        return pool != null ? pool.size() : 0;
    }

}
