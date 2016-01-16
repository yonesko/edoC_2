package gleb.client;

import gleb.server.dao.Sess;
import gleb.server.dao.impl.JobImpl;
import gleb.server.model.Job;
import gleb.server.dao.impl.Factory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JobsTableModel extends AbstractTableModel {

    private List<Job> table;

    public JobsTableModel() {
        table = Factory.getJobImpl().getAllJobs();
    }

    public void insertRow(String job_title, int min_salary, int max_salary) {
        Job newJob = new Job(job_title, min_salary, max_salary);

        Factory.getJobImpl().insertJob(newJob);
        table.add(newJob);

        fireTableDataChanged();
    }

    public void commit() {
        Sess.commit();
    }
    public void rollback() {
        Sess.rollback();
    }

    public int getRowCount() {
        return table.size();
    }

    public int getColumnCount() {
        return JobImpl.getColCount();
    }

    public String getColumnName(int columnIndex) {
        return JobImpl.getColName(columnIndex);
    }

    public Class getColumnClass(int column) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return JobImpl.getFieldValue(table.get(rowIndex), columnIndex);
    }

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
    public void deleteRow(int index) {
        if (index == -1)
            return;
        //delete from base
        Job deletedJob = table.get(index);
        Factory.getJobImpl().deleteJob(deletedJob);
        //delete from view
        table.remove(index);

        fireTableDataChanged();
    }
}