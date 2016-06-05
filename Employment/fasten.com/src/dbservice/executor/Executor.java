package dbservice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate(update);
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            try (ResultSet result = stmt.getResultSet()) {
                T value = handler.handle(result);
                result.close();
                return value;
            }
        }
    }

//    public void commit() throws SQLException {
//        connection.commit();
//    }
//    public void rollback() throws SQLException {
//        connection.rollback();
//    }
}
