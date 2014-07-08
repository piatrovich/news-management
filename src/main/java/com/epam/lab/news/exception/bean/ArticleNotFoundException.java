package com.epam.lab.news.exception.bean;

/**
 * This bean uses for situations when requested data not found.
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "News not found")
public class ArticleNotFoundException extends RuntimeException {
}
