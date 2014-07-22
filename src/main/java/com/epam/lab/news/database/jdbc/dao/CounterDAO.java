package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

@PropertySource("classpath:logger.properties")
public class CounterDAO extends AbstractDAO {

    /** Logger for dao layer */
    private static Logger logger = Logger.getLogger("dao");

    /** Keeps query searching articles counter */
    private static final String SQL_GET_ARTICLE_COUNTER =
            "SELECT _id, count FROM counters WHERE _id = 'aid'";

    /** Keeps query updating articles counter */
    private static final String SQL_UPDATE_ARTICLE_COUNTER =
            "UPDATE counters SET count = ? WHERE _id = ?";

    /** Constructor */
    public CounterDAO(ConnectionPool pool){
        super(pool);
    }

    public Counter get(){
        Counter counter = new Counter();
        connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ARTICLE_COUNTER);
            if(resultSet.next()){
                counter.setId(resultSet.getString(1));
                counter.setCount(resultSet.getLong(2));
            }
        } catch (SQLException e) {

        } finally {
            pool.returnConnection(connection);
        }
        return counter;
    }

    public void update(Counter counter){
        connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ARTICLE_COUNTER);
            preparedStatement.setLong(1, counter.getCount());
            preparedStatement.setBytes(2, counter.getId().getBytes());
            preparedStatement.execute();
        } catch (SQLException e) {

        } finally {
            pool.returnConnection(connection);
        }
    }

}
