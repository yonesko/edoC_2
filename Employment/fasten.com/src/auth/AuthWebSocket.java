package auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbservice.DBService;
import dbservice.models.AccessToken;
import dbservice.models.Msg;
import dbservice.models.User;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.sql.SQLException;

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
    public void onMessage(String data) throws IOException, SQLException {
        // parse JSON data to login and email
        ObjectMapper mapper;
        Msg response;
        Msg msg;
        User user;

        mapper = new ObjectMapper();
        msg = mapper.readValue(data, Msg.class);

        if (msg.getType().equals("LOGIN_CUSTOMER")) {
            user = new User(
                    msg.getData().get("email").toString(),
                    msg.getData().get("password").toString());

            boolean userExists = DBService.getDbService().isUserExists(user);

            if(userExists) {
                AccessToken usersToken = DBService.getDbService().activeTokenOf(user);
                if (usersToken != null) {
                    DBService.getDbService().closeToken(usersToken);
                }

                DBService.getDbService().addTokenTo(user);
                usersToken = DBService.getDbService().activeTokenOf(user);

                response = Msg.getAuthOKMsg(usersToken);
            } else {
                response = Msg.getErrorMsg();
            }

            sendString(mapper.writeValueAsString(response));
        }
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
