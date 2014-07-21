package com.epam.lab.news.aop.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Handles exception which can be thrown from repositories
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class ServiceExceptionInterceptor {
    /** Logger for errors */
    private static Logger logger = Logger.getLogger("errors");

    /**
     * Handles repositories exceptions
     *
     * @param exception If an error has occurred in repositories
     */
    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.database.data.service.NewsService.*(..))",
                   throwing = "exception")
    public void handleNewsServiceExceptions(Throwable exception){
        logger.error("ServiceExceptionInterceptor handle: ", exception);
    }

}
