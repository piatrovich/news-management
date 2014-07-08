package com.epam.lab.news.exception.handler;

import com.epam.lab.news.exception.bean.ArticleNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions if article not found
     *
     * @return Page for 404 error
     */
    @ExceptionHandler(value = ArticleNotFoundException.class)
    public String notFound() {
        return "error/404";
    }

}
