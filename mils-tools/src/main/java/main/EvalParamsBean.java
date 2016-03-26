package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        return SQLtext;
    }

    /**
     разделитель строка с "Query parameters"
     разделитель строки параметров - пробел
     пробельные символы срезаются с конца и начала у:
     входного текста, строки параметров, значении параметра
     */
    public void setSQLtext(String SQLtext) {
        Scanner in = new Scanner(SQLtext.trim());
        String query = "";
        Map<String, String> mParToVal = new HashMap<String, String>();

        //read query
        String line;
        while (in.hasNext() && !(line=in.nextLine()).contains(SECTION_DELIMITER))
            if (!line.matches("\\s*"))
                query += line + '\n';

        if (query.length() == 0)
            query = "Empty query";
        else {
        //read params
            String sParToVal[];
            while (in.hasNext()) {
                line = in.nextLine().trim();
                sParToVal = line.split(" ", 2);
                if (sParToVal.length == 2) {
                    mParToVal.put(sParToVal[0], sParToVal[1].trim());
                }
            }
            if (mParToVal.size() == 0)
                query = String.format("Empty parameters or absent \"%s\" string", SECTION_DELIMITER);
            else {
                //substitute params in query with values
                String val;
                for (Map.Entry<String, String> entry : mParToVal.entrySet()) {
                    val = entry.getValue();
                    if (val != null && entry.getKey() != null) {
                        if (!val.equals("null"))
                            val = '\'' + val + '\'';
                        query = query.replaceAll(entry.getKey(), val);
                    }
                }
            }
        }
        //show result
        this.SQLtext = query.trim();

        logger.info("\nbefore\n {} \nafter\n {}", SQLtext, this.SQLtext);
    }
}