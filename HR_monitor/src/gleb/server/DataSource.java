package gleb.server;

import com.sun.rowset.CachedRowSetImpl;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;

public class DataSource {
    private static Connection connection;
    private static OracleDataSource oraDS;

    static {
        try {
            oraDS = DataSource.getOracleDataSource("hr", "hr");
            connection = oraDS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static OracleDataSource getOracleDataSource(String user, String pass) throws SQLException {
        OracleDataSource oracleDS = null;
        Locale.setDefault(Locale.ENGLISH);

        try {
            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:" + user + "/" + pass + "@//localhost:1521/XE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }

    public static OracleDataSource getOraDS() {
        return oraDS;
    }
}
