package routinewriter.server.templater.model;

import old.ColType;
import old.Column;
import old.Columns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents data for template filling
 */
public class InsertMethod {
    //basic information
    private final String table;
    private final List<Column> cols;
    //cached generated information
    private final List<String> colNames;
    private List<String> vals;
    private List<String> params;
    private Map<String, String> sqlJavaParMap;
    private String pref;

    public InsertMethod(List<String> colNames, String table) {
        this.colNames = colNames;
        this.table = table;
        this.cols = new ArrayList<Column>();
        for (String col : this.colNames)
            cols.add(new Column(col));
    }

    public String getTable() {
        return table;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public List<String> getVals() {
        if (vals == null) {
            vals = new ArrayList<String>();
            pref = findPrefix();
            for (Column col : cols) {
                vals.add(col.getValName().substring(pref.length()));
            }
        }
        return vals;
    }

    private String findPrefix() {
        String result = null, a;
        int minPref = 2;
        if (colNames == null)
            return null;
        for (int i = minPref; i < minLength(); i++) {
            if (!isPrefix(colNames.get(0).substring(0, i)) && i != minPref)
                return colNames.get(0).substring(0, i - 1);
        }
        return result;
    }

    private int minLength() {
        int result = colNames.get(0).length();
        for (int i = 1; i < colNames.size(); i++)
            if (colNames.get(i).length() < result)
                result = colNames.get(i).length();
        return result;
    }

    private boolean isPrefix(String pre) {
        boolean result = true;
        for (String col : colNames)
            if (!col.startsWith(pre)) {
                result = false;
                break;
            }
        return result;
    }

    private String getOISSQLName(String col) {
        return ":" + col.toUpperCase() + ":";
    }

    public List<String> getParams() {
        if (params == null) {
            params = new ArrayList<String>();
            for (Column col : cols)
                params.add(col.getType().getCoreType() + ' ' + col.getJavaName());
        }
        return params;
    }

    public Map<String, String> getSqlJavaParMap() {
        if (this.sqlJavaParMap == null) {
            this.sqlJavaParMap = new HashMap<String, String>();
            for (Column col : cols)
                sqlJavaParMap.put(col.getValName(), col.getJavaName());
        }
        return this.sqlJavaParMap;
    }

    @Override
    public String toString() {
        return "InsertMethod{" +
                "table='" + table + '\'' +
                ", colNames=" + colNames +
                ", vals=" + vals +
                '}';
    }
}
