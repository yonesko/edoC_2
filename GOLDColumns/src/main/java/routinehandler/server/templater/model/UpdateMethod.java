package routinehandler.server.templater.model;

import old.Column;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public UpdateMethod(List<String> setted, List<String> primary, String table, String prefix) {
        super(table, prefix);
        this.primary = new ArrayList<Column>();
        for (String s : primary)
            this.primary.add(new Column(s));

        this.setted = new ArrayList<Column>();
        for (String s : setted)
            this.setted.add(new Column(s));
//            if (primary.contains(s))
//                this.setted.add(new Column(s + "New"));
//            else
//                this.setted.add(new Column(s));
    }

    public Map<String, String> getPrimaryKey() {
        Map<String, String> primaryKey = new LinkedHashMap<String, String>();
        for (Column c : primary)
            primaryKey.put(c.getDbName(), getValName(c.getDbName()));

        return primaryKey;
    }

    public Map<String, String> getSettedCols() {
        Map<String, String> settedCols = new LinkedHashMap<String, String>();
        List<String> primaryNames = new ArrayList<String>();
        for (Column column : primary)
            primaryNames.add(column.getDbName());

        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                settedCols.put(col.getDbName(), getValName(col.getDbName() + "_NEW"));
            else
                settedCols.put(col.getDbName(), getValName(col.getDbName()));
        return settedCols;
    }

    public List<String> getParams() {
        List<String> params = new ArrayList<String>();
        List<String> primaryNames = new ArrayList<String>();
        for (Column column : primary)
            primaryNames.add(column.getDbName());


        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                params.add(col.getType().getCoreType() + ' ' + (col.getDbName() + "New").substring(getPrefix().length()));
            else
                params.add(col.getType().getCoreType() + ' ' + col.getDbName().substring(getPrefix().length()));

        for (Column col : primary)
            params.add(col.getType().getCoreType() + ' ' + col.getDbName().substring(getPrefix().length()));
        return params;
    }

    public Map<String, String> getSqlJavaParMap() {
        LinkedHashMap<String, String> sqlJavaParMap = new LinkedHashMap<String, String>();
        List<String> primaryNames = new ArrayList<String>();
        for (Column column : primary)
            primaryNames.add(column.getDbName());

        for (Column col : setted)
            if (primaryNames.contains(col.getDbName()))
                sqlJavaParMap.put(getValName(col.getDbName() + "_NEW"), (col.getDbName() + "New").substring(getPrefix().length()));
            else
                sqlJavaParMap.put(getValName(col), col.getDbName().substring(getPrefix().length()));

        for (Column col : primary)
            sqlJavaParMap.put(getValName(col), col.getDbName().substring(getPrefix().length()));

        return sqlJavaParMap;
    }
}
