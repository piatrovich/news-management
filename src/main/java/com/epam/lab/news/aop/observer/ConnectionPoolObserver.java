package com.epam.lab.news.aop.observer;

import org.aspectj.lang.annotation.*;

@Aspect
public class ConnectionPoolObserver {

    @Before(value = "@within(javax.annotation.PostConstruct)")       // first way
    public void observeStartInit(){
        System.out.println("Starting init() method!");
    }

    @After(value = "@annotation(javax.annotation.PostConstruct)")   // another way
    //@Pointcut("call(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())")   Solution???
    public void observeFinishInit(){
        System.out.println("Finishing init() method!");
    }

    @AfterReturning(pointcut = "execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())",
                    returning = "size")
    public void checkPoolSize(Integer size){
        System.out.println("After init in pool " + size + " connections.");
    }

    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.getConnection())")
    public void handleTakingConnection(){
        System.out.println("Connection taken from pool.");
    }

    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.returnConnection())")
    public void handleReturningConnection(){
        System.out.println("Connection returned to pool.");
    }

}
