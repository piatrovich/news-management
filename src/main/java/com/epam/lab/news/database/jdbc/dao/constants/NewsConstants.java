package com.epam.lab.news.database.jdbc.dao.constants;

/**
 * Defines queries for working with news
 *
 * @author Dzmitry Piatrovich
 */
public class NewsConstants {
    /** Keeps query for searching all articles */
    public static final String SQL_GET_ALL_NEWS =
            "SELECT _id, title, description, text, date FROM articles";

    /** Keeps query for searching article by unique ID */
    public static final String SQL_GET_NEWS_BY_ID =
            "SELECT _id, title, description, text, date FROM articles WHERE _id = ?";

    /** Keeps query for saving new article */
    public static final String SQL_SAVE_NEWS =
            "INSERT INTO articles (_id, _class, title, description, text, date) VALUES (?, ?, ?, ?, ?, ?)";

    /** Keeps query for updating existing article */
    public static final String SQL_UPDATE_NEWS =
            "UPDATE articles SET title = ? , description = ? , text = ? , date = ? WHERE _id = ?";

    /** Keeps query for deleting existing article */
    public static final String SQL_DELETE_NEWS =
            "DELETE FROM articles WHERE _id = ?";
}
