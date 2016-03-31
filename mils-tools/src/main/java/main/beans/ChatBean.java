package main.beans;

import dbService.DBService;
import dbService.models.ChatMessage;
import main.ChatEndpoint;

import javax.faces.bean.ManagedBean;
import javax.websocket.Session;
import java.util.List;

@ManagedBean
public class ChatBean {
    private String msg;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.trim();
    }

    public String getMsg() {
        return null;
    }

    public void setMsg(String msg) {
        this.msg = msg.trim();
    }

    public void submit() {
        DBService.addMessage(new ChatMessage(msg, author));
    }

    public List<ChatMessage> getChatHistory() {
        return DBService.getChatHistory();
    }

    public static void sendMessage(String message) {
        List<Session> list = ChatEndpoint.getSessions();
        for (Session s : list) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(message);
            }
        }
    }
}
