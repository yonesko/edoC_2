package dbservice;

import auth.AuthService;
import dbservice.models.AccessToken;
import dbservice.models.Msg;

import java.util.HashMap;
import java.util.Map;

public class MsgHelper {
    private static Msg errorMsg;

    public static Msg getErrorMsg() {
        if (errorMsg == null) {
            synchronized (Msg.class) {
                if (errorMsg == null) {
                    HashMap<String, String> respData = new HashMap<>();

                    respData.put("error_description", "Customer not found");
                    respData.put("error_code", "customer.notFound");

                    errorMsg = new Msg(AuthService.CUSTOMER_ERROR, respData);
                }
            }
        }
        return errorMsg;
    }
    public static Msg getAuthOKMsg(AccessToken token) {
        HashMap<String, String> respData = new HashMap<>();

        respData.put("api_token", token.getVal());
        respData.put("api_token_expiration_date", token.getExpiration().toString());

        return new Msg(AuthService.CUSTOMER_API_TOKEN, respData);
    }
}