package gleb.server.dao;

import com.sun.rowset.CachedRowSetImpl;
import gleb.server.LoGGer;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;

public abstract class BaseTable {
    protected CachedRowSet crs;
    /**
     * column_inxex - column_name map; arbitrary case of letters
     */
    protected ArrayList<String> cols;
    protected ArrayList<String> colsNotEditable;
    private ResultSetMetaData meta;

    public ArrayList<String> getCols() {
        return (ArrayList<String>) cols.clone();
    }

    protected int rowsCount = 0;

    private void initRowsCount() throws SQLException {
        crs.beforeFirst();
        while (crs.next())
            rowsCount++;
        crs.beforeFirst();
    }
    protected void initCachedRowSet(String Query) {
        try {
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_SENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUrl(DataSource.getOraDS().getURL());

            crs.setKeyColumns(new int[] {1});
            crs.setCommand(Query);
            crs.execute();
            meta = crs.getMetaData();
            initRowsCount();

            LoGGer.info(crs.getCommand());
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
    protected void setEnabled(String col, boolean a) {
        if (a)
            colsNotEditable.remove(col);
        else
            colsNotEditable.add(col);

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
//        LoGGer.info("getValueAt(" + rowIndex + ", " + columnIndex + ")");
        try {
            crs.absolute(rowIndex + 1);
            Object o = crs.getObject(columnIndex + 1);
            if (o == null)
                return null;
            else
                return o.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setValueAt(Object value, int row, int column) {
        LoGGer.info("setValueAt(" + value + ", " + row + ", " + column + ")");
        column++;
        try {
            int type = crs.getMetaData().getColumnType(column);
            switch (type) {
                case Types.VARCHAR:
                    crs.updateString(column, (String) value);
                    break;
                case Types.NUMERIC:
                    crs.updateDouble(column, Double.parseDouble((String) value));
                    break;
                default:
                    LoGGer.warning("Dont support this type for insertRow");
            }
            crs.updateRow();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
    public ArrayList<Integer> getDisabled() {
        ArrayList<Integer> res = new ArrayList<Integer>();

        for (String s : colsNotEditable) {
            for (int i = 0; i < cols.size(); i++) {
                if (s.equalsIgnoreCase(cols.get(i)))
                    res.add(i);
            }
        }

        return res;
    }
    public void acceptChanges() {
        try {
            crs.acceptChanges();
        } catch (SyncProviderException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
    public int getColCount() {
        try {
            return meta.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getRowsCount() {
        return rowsCount;
    }
    public void deleteRow() {
        try {
            LoGGer.info("deleteRow crs=" + crs.getRow() + cols.get(1 - 1) + " " + crs.getString(1)
                    + "; " + cols.get(2 - 1) + " " + crs.getString(2));
            crs.deleteRow();
            rowsCount--;
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }
}
