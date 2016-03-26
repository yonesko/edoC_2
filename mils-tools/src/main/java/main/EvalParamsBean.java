package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@ManagedBean
@RequestScoped
public class EvalParamsBean {
    private static final Logger logger = LogManager.getLogger();
    private static final String SECTION_DELIMITER = "Query parameters";
    private String SQLtext;

    public String getSQLtext() {
        logger.info("getSQLtext:" + this.SQLtext);
        return SQLtext;
    }

    /**
     разделитель строка с "Query parameters"
     разделитель строки параметров - пробел
     разделитель параметров - \n
     пробельные символы срезаются с конца и начала у:
     входного текста, строки параметров, значении параметра.
     */
    public void setSQLtext(String text) {
        logger.info("setSQLtext:" + text);
        Map<String, String> mParToVal = new HashMap<String, String>();
        String queryAndPars[];
        String paramLines[];
        String parToVal[];
        String result;

        if (text == null) {
            SQLtext = String.format("Cant find \"%s\" delimiter string", SECTION_DELIMITER);
            return;
        }
        //collect params
        queryAndPars = text.split(SECTION_DELIMITER, 2);
        if (queryAndPars.length != 2) {
            SQLtext = String.format("Cant find \"%s\" delimiter string", SECTION_DELIMITER);
            return;
        }
        for (int i = 0; i < queryAndPars.length; i++)
            queryAndPars[i] = queryAndPars[i].trim();

        paramLines = queryAndPars[1].split("\n");
        for (int i = 0; i < paramLines.length; i++) {
            paramLines[i] = paramLines[i].trim();
            parToVal = paramLines[i].split(" ", 2);
            if (parToVal.length == 2) {
                parToVal[1] = parToVal[1].trim();
                if  (!parToVal[1].equals("null"))
                    parToVal[1] = String.format("'%s'", parToVal[1]);
                mParToVal.put(parToVal[0], parToVal[1]);
            }
        }
        //decode text
        result = queryAndPars[0];
        for (Map.Entry<String, String> ent : mParToVal.entrySet())
            result = result.replaceAll(ent.getKey(), ent.getValue());

        result = new BasicFormatterImpl().format(result).trim();
        SQLtext = result;
    }
}