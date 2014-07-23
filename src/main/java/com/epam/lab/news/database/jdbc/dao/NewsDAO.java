package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.jdbc.dao.constants.NewsConstants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for articles manipulation
 *
 * @author Dzmitry Piatrovich
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NewsDAO extends AbstractDAO {

    /**
     * Returns article
     *
     * @param id Unique article ID
     * @return Article object
     */
    public Article get(Long id){
        Article article = new Article();
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_GET_NEWS_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                article.setId(resultSet.getLong(1));
                article.setTitle(resultSet.getString(2));
                article.setDescription(resultSet.getString(3));
                article.setText(resultSet.getString(4));
                article.setDate(resultSet.getDate(5));
            }
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.get.article") + id, e);
        } finally {
            pool.returnConnection(connection);
        }
        return article;
    }

    /**
     * Returns list of all existing articles
     *
     * @return List of articles
     */
    public Iterable<Article> getAll(){
        List<Article> articles = new ArrayList<Article>();
        Connection connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(NewsConstants.SQL_GET_ALL_NEWS);
            while (resultSet.next()){
                Article article = new Article();
                article.setId(resultSet.getLong(1));
                article.setTitle(resultSet.getString(2));
                article.setDescription(resultSet.getString(3));
                article.setText(resultSet.getString(4));
                article.setDate(resultSet.getDate(5));
                articles.add(article);
            }
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.get.all.articles"), e);
        } finally {
            pool.returnConnection(connection);
        }
        return articles;
    }

    /**
     * Saves proposed article
     *
     * @param article Article object
     */
    public void save(Article article){
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_SAVE_NEWS);
            preparedStatement.setLong(1, article.getId());
            preparedStatement.setString(2, article.getClass().getName());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getDescription());
            preparedStatement.setString(5, article.getText());
            preparedStatement.setDate(6, new Date(article.getDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.save.article"), e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    /**
     * Updates existing article by proposed article
     *
     * @param article Article object
     */
    public void update(Article article){
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_UPDATE_NEWS);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getText());
            preparedStatement.setDate(4, new Date(article.getDate().getTime()));
            preparedStatement.setLong(5, article.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.update.article"), e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    /**
     * Deletes existing article ID
     *
     * @param id Unique article ID
     */
    public void delete(Long id){
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_DELETE_NEWS);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("error.dao.delete.article", e);
        } finally {
            pool.returnConnection(connection);
        }
    }

}
