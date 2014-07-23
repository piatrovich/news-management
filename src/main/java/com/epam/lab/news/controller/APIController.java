package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.service.INewsService;
import com.epam.lab.news.validation.ArticleValidator;
import com.epam.lab.news.validation.ValidationResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * Provides public API for working with service
 *
 * @author Dzmitry Piatrovich
 */
@RestController
@RequestMapping("/api/")
@PropertySource("classpath:logger.properties")
public class APIController {
    /** Error logger */
    Logger logger = Logger.getLogger("errors");

    /** Service for working with data using repositories */
    @Autowired
    @Qualifier("newsService")
    INewsService newsService;

    @Autowired
    Environment environment;

    @Autowired
    ArticleValidator validator;

    /**
     * Returns all articles as JSONs
     *
     * @return JSON array
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public Iterable<Article> getArticles() throws Exception{
        return newsService.getAll();
    }

    /**
     * Returns single article in JSON
     *
     * @param id Unique article id
     * @return Article object
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article getArticleForView(@PathVariable Long id) throws Exception{
        return newsService.get(id);
    }

    /**
     * Saves new article
     *
     * @param article Parsed article object from JSON request body
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody ValidationResult newArticle(@RequestBody Article article) throws Exception{
        ValidationResult result = validator.validate(article);
        if(result.isStatus()){
            newsService.save(article);
        }
        return result;
    }

    /**
     * Updating existing article
     *
     * @param article  Parsed article object from JSON request body
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody ValidationResult updateArticle(@RequestBody Article article) throws Exception{
        ValidationResult result = validator.validate(article);
        if(result.isStatus()){
            newsService.update(article);
        }
        return result;
    }

    /**
     * Deleting existing article
     *
     * @param id Unique article id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteArticle(@PathVariable Long id) throws Exception{
        newsService.delete(id);
    }

}