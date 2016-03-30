package main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ChatBean {
    private String msg;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StringBuilder getChatHistory() {
        return ChatHistory.getHist();
    }

    public String getMsg() {
        return null;
    }

    public void setMsg(String msg) {
        ChatHistory.addMsg(author, msg);
    }
}
