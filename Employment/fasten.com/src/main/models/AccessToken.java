package main.models;

import java.sql.Timestamp;

/**
 * Created by gleb on 04.06.16.
 */
public class AccessToken {
    private String val;
    private Timestamp expiration;


    public AccessToken(String val, Timestamp expiration) {
        this.val = val;
        this.expiration = expiration;
    }

    public String getVal() {
        return val;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "val='" + val + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}
