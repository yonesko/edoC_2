package routinehandler.server.templater.model;

import old.Column;

import java.util.*;

/**
 * Represents data for template filling
 */
public class InsertMethod extends AbstractMethod {
    private final List<Column> cols;

    public InsertMethod(List<String> colNames, String table, String prefix) {
        super(table, prefix);
        this.cols = new ArrayList<Column>();
        for (String col : colNames)
            cols.add(new Column(col));
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

    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        for (Column col : cols)
            params.add(col.getType().getCoreType() + ' ' + col.getDbName().substring(getPrefix().length()).toLowerCase());
        return params;
    }

    public Map<String, String> getSqlJavaParMap() {
        Map<String, String> sqlJavaParMap = new LinkedHashMap<String, String>();
        for (Column col : cols)
            sqlJavaParMap.put(getValName(col), col.getDbName().toLowerCase().substring(getPrefix().length()));
        return sqlJavaParMap;
    }
}
