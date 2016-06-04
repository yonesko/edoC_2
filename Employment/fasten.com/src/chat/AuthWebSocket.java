package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class AuthWebSocket {
    private AuthService authService;
    private Session session;

    public AuthWebSocket(AuthService authService) {
        this.authService = authService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        authService.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        //TODO parse JSON data to login and email
        /*TODO select login and email in db
        case 0 then answer is error
        case 1 then select active access token
            case 1 close old access token
            answer is new access token
        */
        authService.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        authService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
