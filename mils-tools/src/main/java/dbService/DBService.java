package dbService;

import dbService.dao.RecallDAO;
import dbService.models.Recall;
import org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:~/gleb-mils-tools";

//            JdbcDataSource ds = new JdbcDataSource();
//            ds.setURL(url);
            DriverManager.registerDriver(new Driver());

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveRecall(String text) {
        RecallDAO recallDAO = new RecallDAO(connection);
        try {
            recallDAO.addRecall(text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recall> getAllRecalls() throws SQLException {
        RecallDAO dao = new RecallDAO(connection);
        return dao.getAllRecalls();
    }

    public void cleanUp() {
        RecallDAO dao = new RecallDAO(connection);
        try {
            dao.dropRecalls();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
