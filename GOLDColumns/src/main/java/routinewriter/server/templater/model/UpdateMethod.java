package routinewriter.server.templater.model;

import old.Column;

import java.util.*;

/**
 * Represents data for template filling
 * if there is intersection between primary keys and
 * setted, sets with new val with _new posfix
 */
public class UpdateMethod {
    //basic information
    private final String table;
    private final List<Column> cols;
    //cached generated information
    private final List<String> colNames;
    private List<String> vals;
    private List<String> params;
    private List<String> primary;
    private List<String> setted;
    private Map<String, String> primaryKey;
    private Map<String, String> settedCols;
    private Map<String, String> sqlJavaParMap;
    private String prefix;

    public UpdateMethod(List<String> setted, List<String> primary, String table) {
        this.colNames = new ArrayList<String>();
        colNames.addAll(setted);
        colNames.addAll(primary);
        this.table = table;
        this.primary = primary;
        this.setted = setted;
        this.cols = new ArrayList<Column>();
        for (String col : this.colNames)
            cols.add(new Column(col));
    }

    public Map<String, String> getPrimaryKey() {
        if (primaryKey == null) {
            primaryKey = new HashMap<String, String>();
            for (String s : primary) {
                primaryKey.put(s, getValName(s));
            }
        }
        return primaryKey;
    }

    public Map<String, String> getSettedCols() {
        if (settedCols == null) {
            settedCols = new HashMap<String, String>();
            for (String col : setted) {
                settedCols.put(col, getValName(col));
            }
        }
        return settedCols;
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
            for (Column col : cols) {
                vals.add(getValName(col));
            }
        }
        return vals;
    }

    private String getValName(Column col) {
        return String.format(":%s:", col.getDbName().substring(getPrefix().length()).toUpperCase());
    }
    private String getValName(String col) {
        return String.format(":%s:", col.substring(getPrefix().length()).toUpperCase());
    }

    public String getPrefix() {
        if (prefix == null)
            prefix = findPrefix();
        return prefix;
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

    public List<String> getParams() {
        for (int i = 0; i < cols.size(); i++) {
            for (int j = i + 1; j < cols.size(); j++) {
                if (cols.get(i).equals(cols.get(j))) {
                    cols.set(j, new Column(cols.get(j).getDbName() + "New"));
                }
            }
        }
        if (params == null) {
            params = new ArrayList<String>();
            for (Column col : cols)
                params.add(col.getType().getCoreType() + ' ' + col.getDbName().substring(getPrefix().length()));
        }
        return params;
    }

    public Map<String, String> getSqlJavaParMap() {
        if (this.sqlJavaParMap == null) {
            this.sqlJavaParMap = new HashMap<String, String>();
            for (Column col : cols)
                sqlJavaParMap.put(getValName(col), col.getDbName().toLowerCase().substring(getPrefix().length()));
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
