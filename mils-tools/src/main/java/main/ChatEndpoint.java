package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;

@ServerEndpoint("/ChatEndpoint")
public class ChatEndpoint {
    private static final Logger logger = LogManager.getLogger();
    private static ArrayList<Session> sessions = new ArrayList<>();

    @OnMessage
    public void messageReceiver(String message) {
        logger.info("Received message:" + message);
    }

    @OnOpen
    public void onOpen(Session session) {
        logger.info("onOpen: " + session.getId());
        sessions.add(session);
        logger.info("onOpen: Notification list size: " + sessions.size());
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }
}
