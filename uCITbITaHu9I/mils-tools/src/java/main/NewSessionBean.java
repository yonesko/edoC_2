/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author gleb
 */
@Stateless
@ViewScoped
@Named
public class NewSessionBean implements Serializable{
    private String incomingText = new String();
    private String resultText = new String();

    public String getIncomingText() {
        return incomingText;
    }

    public void setIncomingText(String incomingText) {
        this.incomingText = incomingText;
    }

    public String getResultText() {
        if (incomingText.isEmpty())
            resultText = "";
        else
            resultText = "OLO";
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    
        
}
