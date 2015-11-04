package gleb.client;

import gleb.server.LoGGer;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class JobsTableModel implements TableModel {

    private CachedRowSet rowSet; // The ResultSet to interpret
    private ResultSetMetaData metadata; // Additional information about the results
    private int numcols, numrows; // How many rows and columns in the table

    public CachedRowSet getRowSet() {
        return rowSet;
    }


    public JobsTableModel(CachedRowSet rowSetArg) throws SQLException {

        this.rowSet = rowSetArg;
        this.metadata = this.rowSet.getMetaData();
        numcols = metadata.getColumnCount();

        // Retrieve the number of rows.
        this.rowSet.beforeFirst();
        this.numrows = 0;
        while (this.rowSet.next()) {
            this.numrows++;
        }
        this.rowSet.beforeFirst();
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.rowSet.addRowSetListener(listener);
    }

    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) throws SQLException {

        try {
            this.rowSet.moveToInsertRow();
            this.rowSet.updateInt("JOB_ID", job_id);
            this.rowSet.updateString("JOB_TITLE", job_title);
            this.rowSet.updateInt("MIN_SALARY", min_salary);
            this.rowSet.updateInt("MAX_SALARY", max_salary);
            this.rowSet.insertRow();
            this.rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }

    public void close() {
        try {
            rowSet.getStatement().close();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }

    /** Automatically close when we're garbage collected */
    protected void finalize() {
        close();
    }

    /** Method from interface TableModel; returns the number of columns */

    public int getColumnCount() {
        return numcols;
    }

    /** Method from interface TableModel; returns the number of rows */

    public int getRowCount() {
        return numrows;
    }

    /** Method from interface TableModel; returns the column name at columnIndex
     *  based on information from ResultSetMetaData
     */

    public String getColumnName(int column) {
        try {
            return this.metadata.getColumnLabel(column + 1);
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
        return null;
    }

    /** Method from interface TableModel; returns the most specific superclass for
     *  all cell values in the specified column. To keep things simple, all data
     *  in the table are converted to String objects; hence, this method returns
     *  the String class.
     */

    public Class getColumnClass(int column) {
        return String.class;
    }

    /** Method from interface TableModel; returns the value for the cell specified
     *  by columnIndex and rowIndex. TableModel uses this method to populate
     *  itself with data from the row set. SQL starts numbering its rows and
     *  columns at 1, but TableModel starts at 0.
     */

    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            this.rowSet.absolute(rowIndex + 1);
            Object o = this.rowSet.getObject(columnIndex + 1);
            if (o == null)
                return null;
            else
                return o.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Method from interface TableModel; returns true if the specified cell
     *  is editable. This sample does not allow users to edit any cells from
     *  the TableModel (rows are added by another window control). Thus,
     *  this method returns false.
     */

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        return true;
    }

    // Because the sample does not allow users to edit any cells from the
    // TableModel, the following methods, setValueAt, addTableModelListener,
    // and removeTableModelListener, do not need to be implemented.

    public void setValueAt(Object value, int row, int column) {
        column++;
        try {
            int type = metadata.getColumnType(column);
            switch (type) {
                case Types.VARCHAR:
                    rowSet.updateString(column, (String) value);
                    break;
//                case Types.INTEGER:
//                    rowSet.updateInt(column, (Integer) value);
//                    break;
                case Types.NUMERIC:
                    rowSet.updateDouble(column, Double.parseDouble((String) value));
                    break;
                default:
                    LoGGer.warning("Dont support this type for update");
            }
            rowSet.updateRow();
        } catch (SQLException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }

}