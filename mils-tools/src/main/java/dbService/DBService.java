package dbService;

import dbService.dao.RecallDAO;
import dbService.models.Recall;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static DBService dbService = new DBService();
    private final Connection connection;

    private DBService() {
        this.connection = getH2Connection();
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:~/gleb-mils-tools";

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
