package routinekiller.server.templater.model;

import old.Column;

import java.util.*;

public abstract class AbstractMethod {
    private final String table;
    private final String prefix;
    private final Map<String, String> colTypeMap = new LinkedHashMap<String, String>();

    //GETERS
    public Map<String, String> getColTypeMap() {
        return colTypeMap;
    }
    public String getPrefix() {
        return prefix;
    }
    //CONSTRUCTORS
    /**
     * @see #AbstractMethod(String, String, String)
     */
    protected AbstractMethod(String table, String prefix) {
        Objects.requireNonNull(table, "table can't be null");
        Objects.requireNonNull(prefix, "prefix can't be null");
        this.table = table;
        this.prefix = prefix == null ? "" : prefix;
    }
    /**
     * @see #parseColTypes(String)
     * @param table Table to be inserted or updated.
     * @param prefix Common for all columns prefix if exists. Default is "". Cutted from Java and insert values parameters.
     * @param colTypeText If null then ColType.NUMBER is default.
     */
    protected AbstractMethod(String table, String prefix, String colTypeText) {
        this(table, prefix);
        parseColTypes(colTypeText);
    }
    /**
     * df122bliv -> bliv
     */
    protected String cutPrfix(Column col) {
        return cutPrfix(col.getDbName());
    }
    /**
     * df122bliv -> :BLIV:
     */
    protected String getValName(Column col) {
        return getValName(col.getDbName());
    }
    /**
     * suffix = "_NEW"<br>
     * df122bliv -> :BLIV_NEW:
     */
    protected String getValName(Column col, String suffix) {
        return getValName(col.getDbName(), suffix);
    }
     public String getTable() {
        return table;
    }
    /**
     * df122bliv -> bliv
     */
    protected String getJavaName(Column col) {
        return  cutPrfix(col).toLowerCase();
    }
    /**
     * suffix = "New"<br>
     * df122bliv -> blivNew
     */
    protected String getJavaName(Column col, String suffix) {
        return  cutPrfix(col).toLowerCase() + suffix;
    }
    /**
     * Inits private column-type map which is used to represent type in Java code generation<br>
     * Default for column type is ColType.NUMBER
     * @param text Database metadata: 2 columns. Values separated by blank.
     */
    private void parseColTypes(String colTypeText) {
        if (colTypeText != null) {
            Scanner scanner = new Scanner(colTypeText);
            while (scanner.hasNextLine())
                colTypeMap.put(scanner.next(), scanner.next());
        }
    }
    /**
     * @see #getValName(Column)
     */
    private String getValName(String col) {
        return String.format(":%s:", cutPrfix(col).toUpperCase());
    }
    /**
     * @see #getValName(Column, String)
     */
    private String getValName(String col, String suffix) {
        return String.format(":%s:", (cutPrfix(col) + suffix).toUpperCase());
    }
    /**
     * @see #cutPrfix(Column)
     */
    private String cutPrfix(String col) {
        String result;
        if (col.toLowerCase().startsWith(prefix.toLowerCase()))
            result = col.substring(prefix.length());
        else
            result = col;
        return result;
    }
}
