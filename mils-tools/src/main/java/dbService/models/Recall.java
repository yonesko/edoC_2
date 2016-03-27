package dbService.models;

import java.sql.Timestamp;

public class Recall {
    private long id;
    private String text;
    private Timestamp created;

    public Recall(long id, String text, Timestamp created) {
        this.id = id;
        this.text = text;
        this.created = created;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Recall{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", created=" + created +
                '}';
    }
}
