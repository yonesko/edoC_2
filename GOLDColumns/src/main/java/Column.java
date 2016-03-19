public class Column {
    //database column name without table prefix
    private final String servName;
    private final String JTableName;
    private final ColType type;
    private final int label;

    public String getJTableName() {
        return this.JTableName;
    }

    public int getLabel() {
        return this.label;
    }

    public String getServName() {
        return this.servName;
    }

    public ColType getType() {
        return this.type;
    }

    public Column(String servName, String cliName, ColType type, int label) {
        this.servName = servName;
        this.JTableName = cliName;
        this.type = type;
        this.label = label;
    }

    public String getCliEditorName() {
        String result = type.getOISTypePrefix() + Character.toUpperCase(servName.charAt(0)) + servName.substring(1).toLowerCase();
        return result;
    }
    public String getInterName() {
        String result = servName.toLowerCase();
        return result;
    }
    public String getOISSQLName() {
        return ":" + servName + ":";
    }
    public String OISRowVal() {
        return "r.to" + getType().getCoreType() + "(\"" + getJTableName() + "\")";
    }
}
