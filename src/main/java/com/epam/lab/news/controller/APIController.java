package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    public void newArticle(@RequestBody Article article) {
        newsService.save(article);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json")
    public void updateArticle(@RequestBody Article article) {
        newsService.saveChanges(article);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteArticle(@PathVariable Long id) {
        newsService.delete(id);
    }

}