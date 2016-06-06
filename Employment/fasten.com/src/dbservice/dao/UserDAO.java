package dbservice.dao;

import dbservice.executor.Executor;
import main.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final Logger logger = LogManager.getLogger();
    private Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }
    /**
     * @return true if pair of email and password exist in one row
     */
    public boolean isUserExists(User user) throws SQLException {
        logger.entry(user);
        String query = String.format(
                "SELECT 1 FROM users " +
                "WHERE password = '%s' " +
                "AND email = '%s'", user.getPassword(), user.getEmail());
        logger.trace(query);
        return logger.exit(executor.execQuery(query, ResultSet::next));
    }
    public void initDB() throws SQLException {
        executor.execUpdate("create table if not exists " +
                "users (" +
                "password varchar(256), " +
                "email varchar(256), " +
                "primary key (email))");

        executor.execUpdate("INSERT INTO users(password, email) VALUES('123', 'gleb@mail.ru')");
        executor.execUpdate("INSERT INTO users(password, email) VALUES('qwer', 'donatella@mail.ru')");
    }

    public void cleanup() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
