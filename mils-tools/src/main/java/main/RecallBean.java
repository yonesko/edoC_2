package main;

import main.dao.RecallSave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RecallBean {
    private static final Logger logger = LogManager.getLogger();
    private String recallText;

    public String getRecallText() {
        return null;
    }

    public void setRecallText(String recallText) {
        logger.info("recall:\n {}", recallText);;
        this.recallText = recallText;
        RecallSave.saveRecall(this.recallText);
    }
}
