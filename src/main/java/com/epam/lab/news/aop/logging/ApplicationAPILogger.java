package com.epam.lab.news.aop.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ApplicationAPILogger {

    @AfterReturning(pointcut = "execution(* com.epam.lab.news.controller.ViewsController.*(..))",
                    returning = "page")
    public void listenReturnedPage(Object page) {
        System.out.println(page);
    }

}
