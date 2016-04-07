package routinehandler.server.templater.model;

import old.Column;

public abstract class AbstractMethod {
    private final String table;
    private final String prefix;

    protected AbstractMethod(String table, String prefix) {
        this.table = table;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    protected String getValName(Column col) {

        return getValName(col.getDbName());
    }

    protected String getValName(String col) {
        return String.format(":%s:", col.substring(prefix.length()).toUpperCase());
    }

    public String getTable() {
        return table;
    }

}
