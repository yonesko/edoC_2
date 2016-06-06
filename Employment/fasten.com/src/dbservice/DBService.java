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

    public DBService() {
        try {
            connection = getH2Connection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.catching(e);
        }
    }

    public boolean isUserExists(User user) throws SQLException {
        UserDAO userDAO = new UserDAO(connection);
        return userDAO.isUserExists(user);
    }

    public AccessToken checkAndAddTokenTo(User user) throws SQLException {
        TokenDAO tokenDAO = new TokenDAO(connection);
        AccessToken accessToken = tokenDAO.checkAndAddTokenTo(user);
        connection.commit();
        return accessToken;
    }

    public void cleanup() throws SQLException {
        TokenDAO tokenDAO = new TokenDAO(connection);
        UserDAO userDAO = new UserDAO(connection);
        tokenDAO.cleanup();
        userDAO.cleanup();
    }

    public void initDB() throws SQLException {
        TokenDAO tokenDAO = new TokenDAO(connection);
        UserDAO userDAO = new UserDAO(connection);
        tokenDAO.initDB();
        userDAO.initDB();
    }

    private static Connection getH2Connection() throws SQLException {
        String url = "jdbc:h2:./h2db";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);

        return DriverManager.getConnection(url);
    }
}
