package main.models;

/**
 * Created by gleb on 04.06.16.
 */
public class User {
    private final String email;
    private final String password;

    public User(String email, String password) {
        if (email == null || password == null)
            throw new NullPointerException(String.format("email = %s, password = %s", email, password));
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
