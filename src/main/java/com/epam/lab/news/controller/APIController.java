package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.data.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class APIController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public Iterable<Article> showIndex() {
        return newsService.getAll();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article showView(@PathVariable Long id) {
        return newsService.get(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Article showEdit(@PathVariable Long id) {
        return newsService.get(id);
    }

}