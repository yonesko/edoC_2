package routinekiller.server.templater.model;

import old.Column;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractMethod {
    private final String table;
    private final String prefix;
    private final Map<String, String> colTypeMap = new HashMap<String, String>();

    //GETERS
    public Map<String, String> getColTypeMap() {
        return colTypeMap;
    }
    public String getPrefix() {
        return prefix;
    }
    //CONSTRUCTORS
    protected AbstractMethod(String table, String prefix) {
        this.table = table;
        this.prefix = prefix == null ? "" : prefix;
    }
    /**
     * @see #parseColTypes(String)
     */
    protected AbstractMethod(String table, String prefix, String colTypeText) {
        this.table = table;
        this.prefix = prefix == null ? "" : prefix;
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
        return getValName(col.getDbName());
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
     * Inits private column-type map which is used to represent type in Java code generation<br>
     * Default for column type is Integer
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
