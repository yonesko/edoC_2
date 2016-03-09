/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gleb
 */
@Named
@SessionScoped
public class QuizBean implements Serializable{
    private int currentProblem = 0;
    private int tries = 0;
    private int score = 0;
    private String response;
    private List<Problem> problems = new ArrayList<Problem>(Arrays.asList(
            new Problem("5+5", "10"),
            new Problem("2*2", "4"),
            new Problem("putin?", "tsar")
    ));

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public String getQuestion() {
        return problems.get(currentProblem).getQuestion();
    }
    
    public String answerAction() {
        tries++;
        if (problems.get(currentProblem).isCorrect(response)) {
            score++;
            nextProblem();
            if(currentProblem == problems.size())
                return "done";
            return "succes";
        } else if(tries == 1)
            return "again";
        else {
            nextProblem();
            if(currentProblem == problems.size())
                return "done";
            else
                return "fail";
        }
    }
    
    private void nextProblem() {
        
    }
}
