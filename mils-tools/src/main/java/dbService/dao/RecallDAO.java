package dbService.dao;

import dbService.executor.Executor;
import dbService.executor.ResultHandler;
import dbService.models.Recall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecallDAO {

    private Executor executor;

    public RecallDAO(Connection connection) {
        this.executor = new Executor(connection);
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recall getRecall(final long id) throws SQLException {
        return executor.execQuery("SELECT id, text FROM recalls WHERE id=" + id, new ResultHandler<Recall>() {
            public Recall handle(ResultSet rs) throws SQLException {
                return new Recall(id, rs.getString("text"));
            }
        });
    }
    
    public List<Recall> getAllRecalls() throws SQLException {
        return executor.execQuery("SELECT id, text FROM recalls", new ResultHandler<List<Recall>>() {
            public List<Recall> handle(ResultSet rs) throws SQLException {
                List<Recall> recalls = new ArrayList<Recall>();
                while (rs.next())
                    recalls.add(new Recall(rs.getLong("id"), rs.getString("text")));
                return null;
            }
        });
    }
    
    public int addRecall(String text) throws SQLException {
        return executor.execUpdate(String.format("INSERT INTO recalls(text) VALUES (%s)", text));
    }

    private void createTable() throws SQLException {
        executor.execUpdate(
                "create table if not exists recalls " +
                "(id bigint identity, " +
                "text VARCHAR2(1000), " +
                "created DATE)"
        );
    }
}
