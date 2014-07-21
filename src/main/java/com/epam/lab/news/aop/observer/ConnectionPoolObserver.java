package com.epam.lab.news.aop.observer;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;

/**
 * Observes for custom pool activity
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class ConnectionPoolObserver {
    /** Pool activity logger */
    private static Logger logger = Logger.getLogger("pool");

    /**
     * Watching start of initialization
     */
    @Before(value = "@within(javax.annotation.PostConstruct)")
    public void observeStartInit(){
        logger.info("Starting init() method!");
    }

    /**
     * Watching end of initialization
     */
    @After(value = "@annotation(javax.annotation.PostConstruct)")
    //@Pointcut("call( public * com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())")  Impossible!
    public void observeFinishInit(){
        logger.info("Finishing init() method!");
    }

    /**
     * Watching pool size
     *
     * @param size Number of connections
     */
    @AfterReturning(pointcut = "execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())",
                    returning = "size")
    public void checkPoolSize(Integer size){
        logger.info("After init in pool " + size + " connections.");
    }

    /**
     * Watching taking connections from pool
     */
    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.getConnection())")
    public void handleTakingConnection(){
        logger.info("Connection taken from pool.");
    }

    /**
     * Watching putting connections to pool
     */
    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.returnConnection())")
    public void handleReturningConnection(){
        logger.info("Connection returned to pool.");
    }

}
