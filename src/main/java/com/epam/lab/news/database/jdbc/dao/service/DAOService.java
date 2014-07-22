package com.epam.lab.news.database.jdbc.dao.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.jdbc.dao.CounterDAO;
import com.epam.lab.news.database.jdbc.dao.NewsDAO;
import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DAOService {
    /** Connection pool */
    @Autowired
    protected ConnectionPool pool;

    public Iterable<Article> getAll() {
        NewsDAO dao = new NewsDAO(pool);
        return dao.getAll();
    }

    public Article get(Long id) {
        NewsDAO dao = new NewsDAO(pool);
        return dao.get(id);
    }

    public void save(Article article) {
        NewsDAO dao = new NewsDAO(pool);
        CounterDAO counterDAO = new CounterDAO(pool);
        Counter counter = counterDAO.get();
        article.setDate(new Date());
        article.setId(counter.getNextId());
        dao.save(article);

    }

    public void update(Article article) {

    }

    public void delete(Long id) {

    }

}
