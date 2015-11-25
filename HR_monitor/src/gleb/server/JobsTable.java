package gleb.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class JobsTable extends BaseTable {

    public JobsTable() {
        colsNotEditable = new ArrayList<>();
        cols = new ArrayList<>(Arrays.asList(
                "job_id"
                ,"job_title"
                ,"min_salary"
                ,"max_salary"));

        initCachedRowSet("SELECT " + String.join(", ", cols) + " FROM jobs_test");
        setEnabled("job_id", false);
    }
    public void insertRow(long job_id, String job_title, int min_salary, int max_salary) {
        try {
            Statement st = DataSource.getOraDS().getConnection().createStatement();
            ResultSet rs = st.executeQuery("select HIBERNATE_SEQUENCE.nextval from dual");
            rs.next();
            job_id = rs.getLong(1);
            LoGGer.info("insertRow job_title=" + job_title + " job_id=" + job_id);

            crs.moveToInsertRow();
            crs.updateLong("JOB_ID", job_id);
            crs.updateString("JOB_TITLE", job_title);
            crs.updateInt("MIN_SALARY", min_salary);
            crs.updateInt("MAX_SALARY", max_salary);
            crs.insertRow();
            rowsCount++;
            crs.moveToCurrentRow();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
}
