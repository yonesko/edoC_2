package gleb;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class JobsTableModel extends AbstractTableModel {
    private List<List<String>> data;

    public JobsTableModel() {
        System.out.println("JobsTableModel");
        data = new ArrayList<>();
    }


    @Override
    public int getRowCount() {
//        System.out.println("getRowCount");
        return data.size();
    }

    @Override
    public int getColumnCount() {
//        System.out.println("getColumnCount");
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        System.out.println("getValueAt");
        return data.get(rowIndex).get(columnIndex);
    }

    public void insertRow(String a, String b) {
        System.out.println("insertRow");
        List<String> l = new ArrayList<>();
        l.add(a);
        l.add(b);
        data.add(l);
//        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
