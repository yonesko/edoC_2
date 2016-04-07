package routinewriter.server.templater.model;

import old.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents data for template filling
 */
public class InsertMethod {
    private final String table;
    private final List<Column> cols;

    public InsertMethod(List<String> colNames, String table) {
        this.table = table;
        this.cols = new ArrayList<Column>();
        for (String col : colNames)
            cols.add(new Column(col));
    }

    public String getTable() {
        return table;
    }

    public List<String> getColNames() {
        List<String> colNames = new ArrayList<String>();
        for (Column col : cols)
            colNames.add(col.getDbName().toLowerCase());
        return colNames;
    }

    public List<String> getVals() {
        List<String> vals = new ArrayList<String>();
        for (Column col : cols)
            vals.add(getValName(col));
        return vals;
    }

    private String getValName(Column col) {
        return String.format(":%s:", col.getDbName().substring(findPrefix().length()).toUpperCase());
    }

    private String findPrefix() {
        String result = "", a;
        int minPref = 2;
        List<String> colNames = getColNames();
        for (int i = minPref; i < minLength(); i++) {
            if (!isPrefix(colNames.get(0).substring(0, i)) && i != minPref)
                return colNames.get(0).substring(0, i - 1);
        }
        return result;
    }

    private int minLength() {
        List<String> colNames = getColNames();
        int result = colNames.get(0).length();
        for (int i = 1; i < colNames.size(); i++)
            if (colNames.get(i).length() < result)
                result = colNames.get(i).length();
        return result;
    }

    private boolean isPrefix(String pre) {
        List<String> colNames = getColNames();
        boolean result = true;
        for (String col : colNames)
            if (!col.startsWith(pre)) {
                result = false;
                break;
            }
        return result;
    }

    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        for (Column col : cols)
            params.add(col.getType().getCoreType() + ' ' + col.getDbName().substring(findPrefix().length()).toLowerCase());
        return params;
    }

    public Map<String, String> getSqlJavaParMap() {
        Map<String, String> sqlJavaParMap = new HashMap<String, String>();
        for (Column col : cols)
            sqlJavaParMap.put(getValName(col), col.getDbName().toLowerCase().substring(findPrefix().length()));
        return sqlJavaParMap;
    }
}
