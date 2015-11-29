package gleb.client;

import gleb.server.dao.entity.Job;
import gleb.server.dao.impl.JobImpl;
import gleb.server.dao.impl.JobsTable;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobsTableModel extends AbstractTableModel {

//    private JobsTable table;
    private List<Job> table;

    public JobsTableModel() {
        table = new JobImpl().getAllJobs();
    }

    public void insertRow(int job_id, String job_title, int min_salary, int max_salary) {
//        table.insertRow(job_id, job_title, min_salary, max_salary);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return table.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
//        return table.getCols().get(columnIndex);
        return String.valueOf(columnIndex);
    }

    public Class getColumnClass(int column) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return table.get(rowIndex).getId();
            case 1:
                return table.get(rowIndex).getTitle();
            case 2:
                return table.get(rowIndex).getMinSalary();
            case 3:
                return table.get(rowIndex).getMaxSalary();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                table.get(rowIndex).setId((Long) value);
            case 1:
                table.get(rowIndex).setTitle((String) value);
            case 2:
                table.get(rowIndex).setMinSalary((Integer) value);
            case 3:
                table.get(rowIndex).setMaxSalary((Integer) value);
        }
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1)
            return false;
        return true;
    }
    public void deleteRow() {
//        table.deleteRow();
        fireTableDataChanged();
    }
}