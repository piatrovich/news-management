package com.epam.lab.news.aop.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ServiceExceptionInterceptor {

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.database.data.service.NewsService.*(..))",
                   throwing = "exception")
    public void handleNewsServiceExceptions(JoinPoint point, Throwable exception){
        System.out.println("ServiceExceptionInterceptor handle: " + exception.getMessage());
    }

}
