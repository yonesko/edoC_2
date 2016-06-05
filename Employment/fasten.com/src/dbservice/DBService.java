package dbservice;

import dbservice.dao.AuthDAO;
import dbservice.executor.Executor;
import main.models.AccessToken;
import main.models.User;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static DBService dbService = new DBService();
    private final Connection connection;

    private DBService() {
        this.connection = getH2Connection();
        try {
            cleanup();
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExists(User user) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        return authDAO.isUserExists(user);
    }

    public AccessToken activeTokenOf(User user) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        return authDAO.activeTokenOf(user);
    }

    public void closeToken(AccessToken token) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        authDAO.closeToken(token);
    }

    public void addTokenTo(User user) throws SQLException {
        AuthDAO authDAO = new AuthDAO(connection);
        authDAO.addTokenTo(user);
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

    private void cleanup() {
        Executor executor = new Executor(connection);
        try {
            executor.execUpdate("drop table users");
            executor.execUpdate("drop table tokens");
        } catch (SQLException e) {
            System.out.println("DBService.cleanup SQLException " + e.getSQLState());
        }
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:~/h2db";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);

            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
