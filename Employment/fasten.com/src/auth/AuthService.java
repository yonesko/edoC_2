package auth;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//TODO use this class
public class AuthService {
    public static final String LOGIN_CUSTOMER = "LOGIN_CUSTOMER";
    public static final String CUSTOMER_ERROR = "CUSTOMER_ERROR";
    public static final String CUSTOMER_API_TOKEN = "CUSTOMER_API_TOKEN";

    private Set<AuthWebSocket> webSockets;

    public AuthService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (AuthWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(AuthWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(AuthWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
