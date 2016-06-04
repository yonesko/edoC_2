package dbservice.models;

import java.sql.Timestamp;

/**
 * Created by gleb on 04.06.16.
 */
public class AccessToken {
    private String val;
    //the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
    private long expiration;


    public AccessToken(String val, long expiration) {
        this.val = val;
        this.expiration = expiration;
    }

    public String getVal() {
        return val;
    }

    public long getExpiration() {
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
