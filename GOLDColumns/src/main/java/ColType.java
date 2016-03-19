public enum ColType {
    NUMBER("odecf", "OISDecimalField", "BigDecimal");
    private final String OISTypePrefix;
    private final String OISType;
    private final String coreType;

    public String getOISTypePrefix() {
        return this.OISTypePrefix;
    }

    public String getCoreType() {
        return this.coreType;
    }

    public String getOISType() {
        return this.OISType;
    }

    ColType(String prefix, String oisType, String coreType) {
        this.OISTypePrefix = prefix;
        this.OISType = oisType;
        this.coreType = coreType;
    }
}
