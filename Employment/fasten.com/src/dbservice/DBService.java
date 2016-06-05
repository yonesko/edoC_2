package dbservice;

import dbservice.dao.AuthDAO;
import dbservice.executor.Executor;
import main.models.AccessToken;
import main.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static DBService dbService = new DBService();
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    private DBService() {
        try {
            connection = getH2Connection();
            connection.setAutoCommit(false);
            cleanup();
            initDB();
        } catch (SQLException e) {
            logger.catching(e);
        }
    }

    public boolean isUserExists(User user) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        return authDAO.isUserExists(user);
    }

    public AccessToken checkAndAddTokenTo(User user) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        AccessToken accessToken = authDAO.checkAndAddTokenTo(user);
        connection.commit();
        return accessToken;
    }

    public static DBService getDbService() {
        return dbService;
    }

    private void initDB() throws SQLException {
        Executor executor = new Executor(connection);
        executor.execUpdate("create table if not exists " +
                "users (" +
                "password varchar(256), " +
                "email varchar(256), " +
                "primary key (email))");
        executor.execUpdate("create table if not exists " +
                "tokens (" +
                "email varchar(256), " +
                "token varchar(256), " +
                "expiration TIMESTAMP," +
                "primary key (token))");

        executor.execUpdate("INSERT INTO users(password, email) VALUES('123', 'gleb@mail.ru')");
        executor.execUpdate("INSERT INTO users(password, email) VALUES('qwer', 'donatella@mail.ru')");
    }

    private void cleanup() throws SQLException {
        Executor executor = new Executor(connection);
        executor.execUpdate("drop table if exists users");
        executor.execUpdate("drop table if exists tokens");
    }

    private static Connection getH2Connection() throws SQLException {
        String url = "jdbc:h2:./h2db";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);

        return DriverManager.getConnection(url);
    }
}
