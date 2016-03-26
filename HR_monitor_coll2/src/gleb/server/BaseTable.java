package gleb.server;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSetListener;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public abstract class BaseTable {
    /**
     * column_inxex - column_name map; arbitrary case of letters
     */
    protected ArrayList<String> cols;
    protected ArrayList<String> colsNotEditable;
    protected ArrayList<List<String>> crs;

    public ArrayList<String> getCols() {
        return (ArrayList<String>) cols.clone();
    }



    protected void initCachedRowSet(String Query) {
        LoGGer.info("init crs");
        crs = new ArrayList<>();
        crs.add(Arrays.asList("AD_VP"	,"Administration Vice President"	,"15000"	,"30000"));
        crs.add(Arrays.asList("AD_PRES"	,"President"	,"20080"	,"40000"));
        crs.add(Arrays.asList("AD_ASST"	,"Administration Assistant"	,"3000"	,"6000"));
        crs.add(Arrays.asList("FI_MGR"	,"Finance Manager"	,"8200"	,"16000"));
        crs.add(Arrays.asList("FI_ACCOUNT"	,"Accountant"	,"4200"	,"9000"));
        crs.add(Arrays.asList("AC_MGR"	,"Accounting Manager"	,"8200"	,"16000"));
        crs.add(Arrays.asList("AC_ACCOUNT"	,"Public Accountant"	,"4200"	,"9000"));
        crs.add(Arrays.asList("SA_MAN"	,"Sales Manager"	,"10000"	,"20080"));
        crs.add(Arrays.asList("SA_REP"	,"Sales Representative"	,"6000"	,"12008"));
        crs.add(Arrays.asList("PU_MAN"	,"Purchasing Manager"	,"8000"	,"15000"));
        crs.add(Arrays.asList("PU_CLERK"	,"Purchasing Clerk"	,"2500"	,"5500"));
        crs.add(Arrays.asList("ST_MAN"	,"Stock Manager"	,"5500"	,"8500"));
        crs.add(Arrays.asList("ST_CLERK"	,"Stock Clerk"	,"2008"	,"5000"));
        crs.add(Arrays.asList("SH_CLERK"	,"Shipping Clerk"	,"2500"	,"5500"));
        crs.add(Arrays.asList("IT_PROG"	,"Programmer"	,"4000"	,"10000"));
        crs.add(Arrays.asList("MK_MAN"	,"Marketing Manager"	,"9000"	,"15000"));
        crs.add(Arrays.asList("MK_REP"	,"Marketing Representative"	,"4000"	,"9000"));
        crs.add(Arrays.asList("HR_REP"	,"Human Resources Representative"	,"4000"	,"9000"));
        crs.add(Arrays.asList("PR_REP"	,"Public Relations Representative"	,"4500"	,"10500"));

    }
    protected void setEnabled(String col, boolean a) {
        if (a)
            colsNotEditable.add(col);
        else
            colsNotEditable.remove(col);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
//        LoGGer.info("getValueAt(" + rowIndex + ", " + columnIndex + ")");
        return crs.get(rowIndex).get(columnIndex);
    }
    public void setValueAt(Object value, int row, int column) {
        LoGGer.info("setValueAt(" + value + ", " + row + ", " + column + ")");
        crs.get(row).set(column, (String) value);
    }
    public ArrayList<Integer> getDisabled() {
        ArrayList<Integer> res = new ArrayList<>();

        for (String s : colsNotEditable) {
            for (int i = 0; i < cols.size(); i++) {
                if (s.equalsIgnoreCase(cols.get(i)))
                    res.add(i);
            }
        }

        return res;
    }
    public void acceptChanges() {
    }
    public int getColCount() {
        return 4;
    }
    public int getRowsCount() {
        return crs.size();
    }
    public void deleteRow(int row) {
        if (row == -1)
            return;
        LoGGer.info("deleteRow row="+row);
        crs.remove(row);
    }
}
