package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class APIController {
    /** Service for working with data */
    private @Autowired NewsService newsService;

    /**
     *
     *
     * @return JSON array
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public Iterable<Article> getArticles() {
        return newsService.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article getArticleForView(@PathVariable Long id) {
        return newsService.get(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article updateArticle(@PathVariable Long id) {
        return newsService.get(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article deleteArticle(@PathVariable Long id) {
        return newsService.get(id);
    }

}