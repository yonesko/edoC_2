package main;

import dbService.DBService;
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
        logger.info("getRecallText:{}", recallText);
        return null;
    }

    public void setRecallText(String recallText) {
        logger.info("setRecallText:{}", recallText);
        this.recallText = recallText;

        DBService dbService = new DBService();
        dbService.saveRecall(recallText);
    }
}
