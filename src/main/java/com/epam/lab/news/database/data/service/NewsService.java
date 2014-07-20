package com.epam.lab.news.database.data.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.data.repo.CounterRepository;
import com.epam.lab.news.database.data.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Defines an application layer between controller and repositories.
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0.alpha
 */
@Service
public class NewsService {
    /** Wiring service with repository for working with Article instances */
    @Autowired
    private NewsRepository repository;

    @Autowired
    private CounterRepository counterRepository;

    /**
     * Returns collection of all articles
     *
     * @return Collection of articles
     */
    public Iterable<Article> getAll() {
        return repository.findAll();
    }

    /**
     * Returns a single article
     *
     * @param id Article id
     * @return Article instance
     */
    public Article get(Long id) {
        //throw new RuntimeException("Exception from newsService");
        return repository.findOne(id);
    }

    public void save(Article article) {
        Counter counter = counterRepository.findOne("aid");
        article.setId(counter.getNextId());
        article.setDate(new Date());
        repository.save(article);
        counterRepository.save(counter);
    }

    public void saveChanges(Article article) {
        article.setDate(new Date());
        repository.save(article);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
