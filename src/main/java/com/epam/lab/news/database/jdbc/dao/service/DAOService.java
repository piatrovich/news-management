package com.epam.lab.news.database.jdbc.dao.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.jdbc.dao.CounterDAO;
import com.epam.lab.news.database.jdbc.dao.NewsDAO;
import com.epam.lab.news.database.service.INewsService;
import com.epam.lab.news.exception.bean.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("daoService")
public class DAOService implements INewsService {

    @Autowired
    private NewsDAO newsRepository;

    @Autowired
    private CounterDAO counterRepository;

    public Iterable<Article> getAll() throws ServiceException {
        return newsRepository.getAll();
    }

    public Article get(Long id) throws ServiceException{
        return newsRepository.get(id);
    }

    public void save(Article article) throws ServiceException{
        Counter counter = counterRepository.get();
        article.setDate(new Date());
        article.setId(counter.getNextId());
        newsRepository.save(article);
    }

    public void update(Article article) throws ServiceException{

    }

    public void delete(Long id) throws ServiceException{

    }

}
