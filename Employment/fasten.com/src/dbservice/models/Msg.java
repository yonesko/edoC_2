package dbservice.models;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //error msg singleton
    private static Msg errorMsg;
    //fields
    private String type;
    private Map<String, Object> data;

    public Msg() {
    }

    public Msg(String type, Map<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public static Msg getErrorMsg() {
        if (errorMsg == null) {
            synchronized (Msg.class) {
                if (errorMsg == null) {
                    HashMap<String, Object> respData = new HashMap<>();

                    respData.put("error_description", "Customer not found");
                    respData.put("error_code", "customer.notFound");

                    errorMsg = new Msg("CUSTOMER_ERROR", respData);
                }
            }
        }
        return errorMsg;
    }
    public static Msg getAuthOKMsg(AccessToken token) {
        HashMap<String, Object> respData = new HashMap<>();

        respData.put("api_token", token.getVal());
        respData.put("api_token_expiration_date", token.getExpiration());

        return new Msg("CUSTOMER_API_TOKEN", respData);
    }
}