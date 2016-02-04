package accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UserProfile implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "pass")
    private String pass;

    public UserProfile() {
    }

    public UserProfile(long id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }

    public long getId() {
        return this.id;
    }

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
