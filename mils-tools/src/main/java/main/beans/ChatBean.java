package main.beans;

import dbService.DBService;
import dbService.models.ChatMessage;
import main.ChatEndpoint;

import javax.faces.bean.ManagedBean;
import javax.websocket.Session;
import java.util.List;

@ManagedBean
public class ChatBean {

//    @PostConstruct
    private void addTestMsgs() {
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
        DBService.addMessage(new ChatMessage("privet", "zaya"));
        DBService.addMessage(new ChatMessage("hello", "anton"));
        DBService.addMessage(new ChatMessage("go chpokt?", "zaya"));
    }

    private String msg;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMsg() {
        return null;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void submit() {
        DBService.addMessage(new ChatMessage(msg, author));
        sendMessage("MSG FROM SERVER HAX " + new ChatMessage(msg, author));
    }

    public List<ChatMessage> getChatHistory() {
        return DBService.getChatHistory();
    }

    public static void sendMessage(String message) {
        List<Session> list = ChatEndpoint.getSessions();
        System.out.println("Notification list size: " + list.size());
        for (Session s : list) {
            if (s.isOpen()) {
                System.out.println("Sending Notification To: " + s.getId());
                s.getAsyncRemote().sendText(message);
            }
        }
    }
}
