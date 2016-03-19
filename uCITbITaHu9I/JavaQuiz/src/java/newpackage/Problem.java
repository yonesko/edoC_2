/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;

/**
 *
 * @author gleb
 */
public class Problem implements Serializable{
    
    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Problem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    
    public boolean isCorrect(String response) {
        return response.trim().equalsIgnoreCase(answer);
    }
}
