package gleb.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;

public class JobsTable extends BaseTable {

    public JobsTable() {
        colsNotEditable = new ArrayList<>();
        cols = new ArrayList<>(Arrays.asList("job_id"
                ,"job_title"
                ,"min_salary"
                ,"max_salary"));

        initCachedRowSet("SELECT " + String.join(", ", cols) + " FROM jobs_test");
        setDisabled("job_id", true);
    }
    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) {
        try {
            crs.moveToInsertRow();
            crs.updateInt("JOB_ID", job_id);
            crs.updateString("JOB_TITLE", job_title);
            crs.updateInt("MIN_SALARY", min_salary);
            crs.updateInt("MAX_SALARY", max_salary);
            crs.insertRow();
            crs.moveToCurrentRow();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
}
