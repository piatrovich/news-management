package com.epam.lab.news.exception.bean;

/**
 * This bean uses for situations when requested data not found.
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
public class ArticleNotFoundException extends DAOException {

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
