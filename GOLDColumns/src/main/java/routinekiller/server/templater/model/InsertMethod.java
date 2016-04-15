package routinekiller.server.templater.model;

import old.ColType;
import old.Column;

import java.util.*;

/**
 * Generates data for template filling
 */
public class InsertMethod extends AbstractMethod {
    private final List<Column> cols;
    /**
     * @see #AbstractMethod(String, String, String)
     * @param colNames Names of columns from database to be inserted.
     */
    public InsertMethod(List<String> colNames, String table, String prefix, String colTypeText) {
        super(table, prefix, colTypeText);
        this.cols = new ArrayList<Column>();
        for (String col : colNames)
            cols.add(new Column(col));

        for (Map.Entry<String, String> e : getColTypeMap().entrySet())
            for (Column col : cols)
                if (col.getDbName().equalsIgnoreCase(e.getKey()))
                    col.setType(ColType.valueOf(e.getValue()));
    }
    /**
     * For example evaluets in template into: <pre>String bliv, BigDecimal calc, String contrnumfim, String contrnumfou</pre>
     */
    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        for (Column col : cols)
            params.add(col.getType().getJavaType() + ' ' + getJavaName(col));
        return params;
    }
    /**
     * For example evaluets in template into: <pre>df7bliv, df7calc, df7contrnumfim, df7contrnumfou</pre>
     */
    public List<String> getColNames() {
        List<String> colNames = new ArrayList<String>();
        for (Column col : cols)
            colNames.add(col.getDbName().toLowerCase());
        return colNames;
    }
    /**
     * For example evaluets in template into: <pre>:BLIV:, :CALC:, :CONTRNUMFIM:, :CONTRNUMFOU:</pre>
     */
    public List<String> getVals() {
        List<String> vals = new ArrayList<String>();
        for (Column col : cols)
            vals.add(getValName(col));
        return vals;
    }

    /**
     * For example evaluets in template into:<pre>
     *":BLIV:", bliv,
      ":CALC:", calc,
      ":CONTRNUMFIM:", contrnumfim</pre>
     */
    public Map<String, String> getSqlJavaParMap() {
        Map<String, String> sqlJavaParMap = new LinkedHashMap<String, String>();
        for (Column col : cols)
            sqlJavaParMap.put(getValName(col), getJavaName(col));
        return sqlJavaParMap;
    }
}
