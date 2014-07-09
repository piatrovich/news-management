package com.epam.lab.news.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Defines MongoDB configuration
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.epam.lab.news.data.repo")
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

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo(), DATABASE);
    }

    /**
     * Returns Mongo object initialized by custom pool options.
     *
     * @return Mongo object
     * @throws Exception if database cannot be resolved
     */
    @Override
    public Mongo mongo() throws Exception {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(8)
                .threadsAllowedToBlockForConnectionMultiplier(4)
                .connectTimeout(100)
                .maxWaitTime(500)
                .socketKeepAlive(true)
                .socketTimeout(500)
                .build();
        return new MongoClient(new ServerAddress(HOST, PORT), options);
    }

}
