/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gleb
 */
@Named
@SessionScoped
public class NewClass implements Serializable{
    
    private String name = new String();

    private String pass = new String();
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        System.out.println("name=" + pass);
        this.pass = pass;
    }

    public String getGreeting() {
        System.out.println("getGreeting:name=" + name);
        if (name.length() == 0)
            return "";
        else
            return "Privet " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("name=" + name);
        this.name = name;
    }

    public String getMsg() {
        return "Privet blya!";
    }
}
