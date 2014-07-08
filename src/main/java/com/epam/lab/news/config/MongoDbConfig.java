package com.epam.lab.news.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Defines MongoDB configuration
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Configuration
public class MongoDbConfig extends AbstractMongoConfiguration {
    /** Keeps database name */
    private static final String DATABASE = "news";

    /** Keeps host */
    private static final String HOST = "localhost";

    /** Keeps post for access */
    private static final int PORT = 27017;

    /**
     * Returns database name.
     * Override if You are using own database.
     *
     * @return Database name
     */
    @Override
    protected String getDatabaseName() {
        return DATABASE;
    }

    /**
     * Returns Mongo object which contains internal pool with 100 connections.
     *
     * @return Mongo object
     * @throws Exception if database cannot be resolved
     */
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(HOST, PORT);
    }

}
