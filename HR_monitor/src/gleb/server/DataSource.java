package gleb.server;

import com.sun.rowset.CachedRowSetImpl;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
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
    public static CachedRowSet getContentsOfCoffeesTable() throws SQLException {
        CachedRowSet crs = null;
        try {
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUsername("hr");
            crs.setPassword("hr");
            crs.setUrl("jdbc:oracle:thin:" + "hr" + "/" + "hr" + "@//localhost:1521/XE");

            // Regardless of the query, fetch the contents of COFFEES
            crs.setCommand("SELECT job_id, job_title, min_salary, max_salary FROM jobs");
            crs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crs;
    }
}
