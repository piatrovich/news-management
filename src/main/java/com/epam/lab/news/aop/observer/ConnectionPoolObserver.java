package com.epam.lab.news.aop.observer;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;

@Aspect
public class ConnectionPoolObserver {
    private static Logger logger = Logger.getLogger("pool");

    @Before(value = "@within(javax.annotation.PostConstruct)")       // first way
    public void observeStartInit(){
        logger.info("Starting init() method!");
    }

    @After(value = "@annotation(javax.annotation.PostConstruct)")   // another way
    //@Pointcut("call( public * com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())")  It's not possible!
    public void observeFinishInit(){
        logger.info("Finishing init() method!");
    }

    @AfterReturning(pointcut = "execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())",
                    returning = "size")
    public void checkPoolSize(Integer size){
        logger.info("After init in pool " + size + " connections.");
    }

    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.getConnection())")
    public void handleTakingConnection(){
        logger.info("Connection taken from pool.");
    }

    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.returnConnection())")
    public void handleReturningConnection(){
        logger.info("Connection returned to pool.");
    }

}
