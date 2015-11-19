package gleb.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class JobsTable extends BaseTable {

    public JobsTable() {
        colsNotEditable = new ArrayList<>();
        cols = new ArrayList<>(Arrays.asList("job_id"
                ,"job_title"
                ,"min_salary"
                ,"max_salary"));

        initCachedRowSet("SELECT " + String.join(", ", cols) + " FROM jobs_test");
        setEnabled("job_id", true);
    }
    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) {
        LoGGer.info("insertRow job_title=" + job_title + " job_id=" + job_id);
        try {
            crs.add(
                    Arrays.asList(Integer.toString(job_id)
                            ,job_title
                            ,Integer.toString(min_salary)
                            ,Integer.toString(max_salary)));
        } catch (Exception e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
}
