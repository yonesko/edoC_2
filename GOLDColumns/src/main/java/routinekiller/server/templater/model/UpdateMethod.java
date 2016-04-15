package routinekiller.server.templater.model;

import old.ColType;
import old.Column;

import java.util.*;

/**
 * Represents data for template filling
 * if there is intersection between primary keys and
 * setted, sets with new val with _new posfix
 * all getters are used by freemaker template
 */
public class UpdateMethod extends AbstractMethod{
    //basic information
    private final List<Column> setted;
    private final List<Column> primary;

    /**
     * If columns exists both in setted and primary lists then setted variant appears in tempate with "new" suffix.
     * @param setted Columns that will be in "WHERE" clause of resultant template.
     * @param primary Columns that will be in "SET" clause of resultant template.
     */
    public UpdateMethod(List<String> setted, List<String> primary, String table, String prefix, String colTypeText) {
        super(table, prefix, colTypeText);
        Collections.sort(setted);
        Collections.sort(primary);
        this.primary = new ArrayList<Column>();
        for (String s : primary)
            this.primary.add(new Column(s));

        this.setted = new ArrayList<Column>();
        for (String s : setted)
            this.setted.add(new Column(s));

        for (Map.Entry<String, String> e : getColTypeMap().entrySet())
            for (Column col : this.primary)
                if (col.getDbName().equalsIgnoreCase(e.getKey()))
                    col.setType(ColType.valueOf(e.getValue()));

        for (Map.Entry<String, String> e : getColTypeMap().entrySet())
            for (Column col : this.setted)
                if (col.getDbName().equalsIgnoreCase(e.getKey()))
                    col.setType(ColType.valueOf(e.getValue()));
    }
    /**
     * For example evaluets in template into: <pre>String bliv, String blivNew, BigDecimal calc, String contrnumfim, String contrnumfou</pre>
     */
    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        List<String> primaryNames = new ArrayList<String>();

        for (Column column : primary)
            primaryNames.add(column.getDbName());
        //set new name to duplicate
        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                params.add(col.getType().getJavaType() + ' ' + getJavaName(col, "New"));
            else
                params.add(col.getType().getJavaType() + ' ' + getJavaName(col));

        for (Column col : primary)
            params.add(col.getType().getJavaType() + ' ' + getJavaName(col));

        return params;
    }

    /**
     * Responsible for "WHERE" clause
     * For example evaluets in template into: <pre>
     * df7bliv = :BLIV: AND
       rowid = :ROWID:</pre>
     */
    public Map<String, String> getPrimaryKey() {
        Map<String, String> primaryKey = new LinkedHashMap<String, String>();

        for (Column c : primary)
            primaryKey.put(c.getDbName(), getValName(c));

        return primaryKey;
    }
    /**
     * Responsible for "SET" clause
     * For example evaluets in template into: <pre>
     * df7bliv = :BLIV_NEW:,
       df7calc = :CALC:</pre>
     */
    public Map<String, String> getSettedCols() {
        Map<String, String> settedCols = new LinkedHashMap<String, String>();
        List<String> primaryNames = new ArrayList<String>();

        for (Column column : primary)
            primaryNames.add(column.getDbName());

        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                settedCols.put(col.getDbName(), getValName(col, "_NEW"));
            else
                settedCols.put(col.getDbName(), getValName(col));

        return settedCols;
    }
    /**
     * For example evaluets in template into:<pre>
     *":BLIV:", bliv,
      ":CALC:", calc,
      ":CONTRNUMFIM:", contrnumfim</pre>
     */
    public Map<String, String> getSqlJavaParMap() {
        LinkedHashMap<String, String> sqlJavaParMap = new LinkedHashMap<String, String>();
        List<String> primaryNames = new ArrayList<String>();

        for (Column column : primary)
            primaryNames.add(column.getDbName());

        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                sqlJavaParMap.put(getValName(col, "_NEW"), getJavaName(col, "New"));
            else
                sqlJavaParMap.put(getValName(col), getJavaName(col));

        for (Column col : primary)
            sqlJavaParMap.put(getValName(col), getJavaName(col));

        return sqlJavaParMap;
    }
}
