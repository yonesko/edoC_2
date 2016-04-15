package old;

public enum ColType {
    NUMBER("BigDecimal"),
    VARCHAR2("String"),
    DATE("String");

    private final String javaType;

    public String getJavaType() {
        return this.javaType;
    }

    ColType(String javaType) {
        this.javaType = javaType;
    }
}
