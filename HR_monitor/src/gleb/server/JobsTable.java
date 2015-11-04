package gleb.server;

import com.sun.rowset.CachedRowSetImpl;
import gleb.server.DataSource;
import gleb.server.LoGGer;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class JobsTable {
    private CachedRowSet crs;
    private String[] cols;

    public CachedRowSet getCrs() {
        return crs;
    }

    public String[] getCols() {
        return cols;
    }

    public JobsTable() {
        cols = new String[] {
                "job_id"
                ,"job_title"
                ,"min_salary"
                ,"max_salary"

        };
        try {
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_SENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUrl(DataSource.getOraDS().getURL());

            // Regardless of the query, fetch the contents of COFFEES
            crs.setCommand("SELECT " + String.join(", ", cols) + " FROM jobs_test");
            LoGGer.info(crs.getCommand());
            crs.execute();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
}
