package dbservice.models;

import java.util.Map;

public class Msg {
    private String type;
    private Map<String, String> data;

    public Msg() {
    }

    public Msg(String type, Map<String, String> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getData() {
        return data;
    }
}