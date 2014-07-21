package com.epam.lab.news.aop.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ServiceExceptionInterceptor {
    private static Logger logger = Logger.getLogger("errors");

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.database.data.service.NewsService.*(..))",
                   throwing = "exception")
    public void handleNewsServiceExceptions(Throwable exception){
        logger.error("ServiceExceptionInterceptor handle: ", exception);
    }

}
