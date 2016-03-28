package main;

import dbService.DBService;
import dbService.dao.RecallDAO;
import dbService.models.Recall;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gleb on 26.03.16.
 */
public class RecallBeanTest {

    @Test
    public void setRecallText() throws Exception {
        RecallBean bean = new RecallBean();

        bean.setRecallText("OCHEN KROTO HAX");

        DBService service = new DBService();
        for (Recall recall : service.getAllRecalls())
            System.out.println(recall);

        service.cleanUp();
    }
}