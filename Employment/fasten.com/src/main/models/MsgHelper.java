package main.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.models.AccessToken;
import main.models.Msg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public class MsgHelper {
    private static final Logger logger = LogManager.getLogger();
    private static Msg errorMsg;

    static {
        HashMap<String, String> respData = new HashMap<>();

        respData.put("error_description", "Customer not found");
        respData.put("error_code", "customer.notFound");

        errorMsg = new Msg(Msg.Type.CUSTOMER_ERROR, respData);
    }

    /**
     * @return cached login fail msg
     */
    public static Msg getAuthErrMsg() {
        return errorMsg;
    }

    public static Msg getAuthOKMsg(AccessToken token) {
        HashMap<String, String> respData = new HashMap<>();

        respData.put("api_token", token.getVal());
        respData.put("api_token_expiration_date", token.getExpiration().toString());

        return new Msg(Msg.Type.CUSTOMER_API_TOKEN, respData);
    }
    public static Msg parseMsg(String data) {
        ObjectMapper mapper;
        Msg msg = null;

        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            msg = mapper.readValue(data, Msg.class);
        } catch (IOException e) {
            logger.catching(e);
        }

        return msg;
    }
}