package gleb.client;

import gleb.server.JobsTable;
import javax.sql.RowSetListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class JobsTableModel implements TableModel {

    private JobsTable table;

    public JobsTableModel() {
        table = new JobsTable();
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        table.addRowSetListener(listener);
    }

    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) {
        table.insertRow(job_id, job_title, min_salary, max_salary);
    }

    @Override
    public int getRowCount() {
        return table.getRowsCount();
    }

    @Override
    public int getColumnCount() {
        return table.getColCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    public Class getColumnClass(int column) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return table.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        table.setValueAt(value, rowIndex, columnIndex);
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (table.getDisabled().contains(columnIndex))
            return false;
        return true;
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }
    public void acceptChanges() {
        table.acceptChanges();
    }

}