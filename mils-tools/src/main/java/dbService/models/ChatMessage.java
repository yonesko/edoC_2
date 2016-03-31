package dbService.models;

import java.io.Serializable;

public class ChatMessage {
    private String content;
    private String author;

    public ChatMessage(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
