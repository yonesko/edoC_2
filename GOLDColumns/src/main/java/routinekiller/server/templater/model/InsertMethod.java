package routinekiller.server.templater.model;

import old.ColType;
import old.Column;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates data for template filling
 */
public class InsertMethod extends AbstractMethod {
    private final List<Column> cols;
    /**
     *
     * @param colNames Names of columns from database to be inserted.
     * @param table Table to be inserted.
     * @param prefix Common for all columns prefix if exists. Default is "". Cutted from Java and insert values parameters.
     * @param colTypeText If null then ColType.NUMBER is default.
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
     * For example evaluets in template into "String bliv, BigDecimal calc, String contrnumfim, String contrnumfou".
     */
    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        for (Column col : cols)
            params.add(col.getType().getJavaType() + ' ' + getJavaName(col));
        return params;
    }

    /**
     * For example evaluets in template into "df7bliv, df7calc, df7contrnumfim, df7contrnumfou".
     */
    public List<String> getColNames() {
        List<String> colNames = new ArrayList<String>();
        for (Column col : cols)
            colNames.add(col.getDbName().toLowerCase());
        return colNames;
    }

    /**
     * For example evaluets in template into ":BLIV:, :CALC:, :CONTRNUMFIM:, :CONTRNUMFOU:".
     */
    public List<String> getVals() {
        List<String> vals = new ArrayList<String>();
        for (Column col : cols)
            vals.add(getValName(col));
        return vals;
    }

    public Map<String, String> getSqlJavaParMap() {
        Map<String, String> sqlJavaParMap = new LinkedHashMap<String, String>();
        for (Column col : cols)
            sqlJavaParMap.put(getValName(col), getJavaName(col));
        return sqlJavaParMap;
    }
}
