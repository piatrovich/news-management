package com.epam.lab.news.exception.handler;

import com.epam.lab.news.exception.bean.ArticleNotFoundException;
import com.epam.lab.news.exception.bean.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     */
    @ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<Object> handleControllerException() {
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArticleNotFoundException.class)
    public ResponseEntity<Object> handleArticleNotFound() {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

}
