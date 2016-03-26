package gleb.client;

import gleb.server.JobsTable;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class JobsTableModel extends AbstractTableModel {

    private JobsTable table;

    public JobsTableModel() {
        table = new JobsTable();
    }

    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) {
        table.insertRow(job_id, job_title, min_salary, max_salary);
//        acceptChanges();
//        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
        fireTableDataChanged();
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
        return table.getCols().get(columnIndex);
    }

    public Class getColumnClass(int column) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
//        System.out.println("getValueAt");
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
    public void acceptChanges() {
        table.acceptChanges();
    }
    public void deleteRow(int row) {
        table.deleteRow(row);
        fireTableDataChanged();
    }
}