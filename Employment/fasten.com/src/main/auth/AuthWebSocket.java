package main.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.models.MsgHelper;
import main.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import main.models.Msg;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class AuthWebSocket {
    private static final Logger logger = LogManager.getLogger();
    private AuthService authService;
    private Session session;

    public AuthWebSocket(AuthService authService) {
        this.authService = authService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        logger.trace("local={}, remote={}", session.getLocalAddress(), session.getRemoteAddress());
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        Msg msg;
        Msg response = null;

        msg = MsgHelper.parseMsg(data);

        if (msg != null && msg.getType() == Msg.Type.LOGIN_CUSTOMER) {
            User user;

            user = new User(
                    msg.getData().get("email").intern(),
                    msg.getData().get("password"));

            try {
                synchronized (user.getEmail()) {
                    response = authService.authorize(user);
                }
            } catch (Exception e) {
                logger.catching(e);
            }
        }

        sendMsg(response);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        logger.trace("local={}, remote={}", session.getLocalAddress(), session.getRemoteAddress());
        logger.trace("statusCode={}, reason={}", statusCode, reason);
    }

    public void sendMsg(Msg msg) {
        ObjectMapper mapper;
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            sendString(mapper.writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            logger.catching(e);
        }
    }

    public void sendString(String data) {
        try {
            if (session.isOpen()) {
                session.getRemote().sendString(data);
            }
        } catch (Exception e) {
            logger.catching(e);
        }
    }
}
