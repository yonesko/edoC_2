package main.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class GifBean {
    private String link = "http://tv.giphy.com/cat";

    public String getLink() {
        return link;
    }
}