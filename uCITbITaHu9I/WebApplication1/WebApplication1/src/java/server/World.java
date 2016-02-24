package server;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("world")
@SessionScoped
public class World implements Serializable {
    private String str = "world";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    
}
