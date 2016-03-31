package dbService;

import dbService.dao.ChatHistoryDAO;
import dbService.dao.RecallDAO;
import dbService.models.ChatMessage;
import dbService.models.Recall;
import org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private static Connection connection;

    private DBService() {}

    private static Connection getH2Connection() {
        if (connection != null)
            return connection;
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

    public static void saveRecall(String text) {
        RecallDAO recallDAO = new RecallDAO(getH2Connection());
        try {
            recallDAO.addRecall(text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Recall> getAllRecalls() throws SQLException {
        RecallDAO dao = new RecallDAO(getH2Connection());
        return dao.getAllRecalls();
    }

    public static void cleanUp() {
        RecallDAO dao = new RecallDAO(getH2Connection());
        try {
            dao.dropRecalls();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ChatMessage> getChatHistory() {
        return ChatHistoryDAO.getChatHistory();
    }

    public static void addMessage(ChatMessage msg) {
        ChatHistoryDAO.addMessage(msg);
    }
}
