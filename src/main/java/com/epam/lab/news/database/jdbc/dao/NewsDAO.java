package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for articles manipulation
 *
 * @author Dzmitry Piatrovich
 */
@PropertySource("classpath:logger.properties")
public class NewsDAO extends AbstractDAO {

    /** Logger for dao layer */
    private static Logger logger = Logger.getLogger("dao");

    /** Keeps query for searching all articles */
    private static final String SQL_GET_ALL_NEWS =
            "SELECT _id, title, description, text, date FROM articles";

    /** Keeps query for searching article by unique ID */
    private static final String SQL_GET_NEWS_BY_ID =
            "SELECT _id, title, description, text, date FROM articles WHERE _id = ?";

    /** Keeps query for saving new article */
    private static final String SQL_SAVE_NEWS =
            "INSERT INTO articles (_id, _class, title, description, text, date) VALUES (?, ?, ?, ?, ?, ?)";

    /** Keeps query for updating existing article */
    private static final String SQL_UPDATE_NEWS =
            "UPDATE articles SET title = ? , description = ? , text = ? , date = ? WHERE _id = ?";

    /** Keeps query for deleting existing article */
    private static final String SQL_DELETE_NEWS =
            "DELETE FROM articles WHERE _id = ?";

    /** Constructor */
    public NewsDAO(ConnectionPool pool){
        super(pool);
    }

    /**
     * Returns article
     *
     * @param id Unique article ID
     * @return Article object
     */
    public Article get(Long id){
        Article article = new Article();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_NEWS_BY_ID);
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
            //logger.error(env.getProperty("error.dao.get.article") + id, e);
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
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_NEWS);
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
            //logger.error(env.getProperty("error.dao.get.all.articles"), e);
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
        connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SAVE_NEWS);
            preparedStatement.setLong(1, article.getId());
            preparedStatement.setString(2, article.getClass().getName());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getDescription());
            preparedStatement.setString(5, article.getText());
            preparedStatement.setDate(6, new Date(article.getDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            //logger.error(env.getProperty("error.dao.save.article"), e);
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
        connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS);
            preparedStatement.setBytes(3, article.getTitle().getBytes());
            preparedStatement.setBytes(4, article.getDescription().getBytes());
            preparedStatement.setBytes(5, article.getText().getBytes());
            preparedStatement.setDate(6, new Date(article.getDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            //logger.error(env.getProperty("error.dao.update.article"), e);
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
        connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            //logger.error("error.dao.delete.article", e);
        } finally {
            pool.returnConnection(connection);
        }
    }

}
