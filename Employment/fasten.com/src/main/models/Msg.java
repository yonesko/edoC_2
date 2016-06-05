package main.models;

import java.util.Map;

/**
 * Transfer object for client-server interconnection
 */
public class Msg {
    private Type type;
    private Map<String, String> data;

    public Msg() {
    }

    public Msg(Type type, Map<String, String> data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public Map<String, String> getData() {
        return data;
    }

    public enum Type {
        LOGIN_CUSTOMER,
        CUSTOMER_ERROR,
        CUSTOMER_API_TOKEN;
    }
}