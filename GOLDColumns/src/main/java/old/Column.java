package old;

public class Column {
    //database column name without table prefix
    private final String dbName;
    private String JTableName;
    private ColType type;
    private int label;

    //default values
    {
        type = ColType.INTEGER;
    }

    public Column(String dbName) {
        this.dbName = dbName;
    }

    public String getValName() {
        return ':' + dbName.toUpperCase() + ':';
    }

    public String getJavaName() {
        return dbName.toLowerCase();
    }
    //Getters
    public String getDbName() {
        return dbName;
    }

    public String getJTableName() {
        return JTableName;
    }

    public ColType getType() {
        return type;
    }

    public int getLabel() {
        return label;
    }

    //Setters
    public void setJTableName(String JTableName) {
        this.JTableName = JTableName;
    }

    public void setType(ColType type) {
        this.type = type;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
