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
        if (dbName == null || dbName.length() == 0)
            throw new IllegalArgumentException();
        this.dbName = dbName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        return dbName.equals(column.dbName);

    }

    @Override
    public int hashCode() {
        return dbName.hashCode();
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
