package com.epam.lab.news.aop.logging;

import com.epam.lab.news.bean.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Aspect
public class ApplicationAPILogger {
    private static Logger apiLogger = Logger.getLogger("api");
    private static Logger errorLogger = Logger.getLogger("errors");

    @AfterReturning(pointcut = "execution(* com.epam.lab.news.controller.APIController.getArticleForView(..))",
                    returning = "article")
    public void listenReturnedPage(Article article) {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(format);
        try {
            apiLogger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article));
        } catch (JsonProcessingException e) {
            errorLogger.error(e);
        }
    }

}
