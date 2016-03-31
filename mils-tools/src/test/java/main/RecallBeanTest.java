package main;

import dbService.DBService;
import dbService.models.Recall;
import main.beans.RecallBean;
import org.junit.Test;

/**
 * Created by gleb on 26.03.16.
 */
public class RecallBeanTest {

    @Test
    public void setRecallText() throws Exception {
        RecallBean bean = new RecallBean();

        bean.setRecallText("OCHEN KROTO HAX");

        for (Recall recall : DBService.getAllRecalls())
            System.out.println(recall);

        DBService.cleanUp();
    }
}