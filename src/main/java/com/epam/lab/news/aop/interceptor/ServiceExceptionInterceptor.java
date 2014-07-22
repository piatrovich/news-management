package com.epam.lab.news.aop.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Handles exception which can be thrown from repositories
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
@PropertySource("classpath:logger.properties")
public class ServiceExceptionInterceptor {
    /** Logger for errors */
    private static Logger logger = Logger.getLogger("errors");

    /**
     * Provides access to property sources
     */
    @Autowired
    private Environment environment;

    /**
     * Handles repositories exceptions
     *
     * @param exception If an error has occurred in repositories
     */
    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.database.data.service.NewsService.*(..))",
                   throwing = "exception")
    public void handleNewsServiceExceptions(Throwable exception){
        logger.error(environment.getProperty("error.news.service.interceptor"), exception);
    }

}
