package gleb.server;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.SQLException;
import java.util.Locale;

public class DataSource {
    public static OracleDataSource getOracleDataSource(String user, String pass) throws SQLException {
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
}
