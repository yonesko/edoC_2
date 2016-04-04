package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

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
        logger.debug("onOpen: " + session.getId());
        sessions.add(session);
        logger.debug("onOpen: Notification list size: " + sessions.size());
    }

    @OnClose
    public void OnClose(Session session) {
        logger.debug("OnClose: " + session.getId());
        sessions.remove(session);
        logger.debug("OnClose: Notification list size: " + sessions.size());
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

    public static void notifyAllSockets() {
        List<Session> list = ChatEndpoint.getSessions();
        for (Session s : list)
            if (s.isOpen())
                s.getAsyncRemote().sendText("notification");
    }
}
