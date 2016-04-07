package main.beans;

import dbService.DBService;
import dbService.models.ChatMessage;
import main.ChatEndpoint;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;
import java.util.List;

@ManagedBean
@RequestScoped
public class ChatBean {
    @Size(min = 1, max = 100, message = "Длина от {min} до {max}")
    private String msg;
    @Size(min = 1, max = 10, message = "Длина от {min} до {max}")
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
        ChatEndpoint.notifyAllSockets();
    }

    public List<ChatMessage> getChatHistory() {
        return DBService.getChatHistory();
    }
}
