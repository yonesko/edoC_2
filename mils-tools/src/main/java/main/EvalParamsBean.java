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

    public void setSQLtext(String SQLtext) {
        Scanner in = new Scanner(SQLtext);
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
                line = in.nextLine();
                if (!line.matches("\\s*")) {
                    sParToVal = line.split("\\s+");
                    mParToVal.put(sParToVal[0], sParToVal[1]);
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
        this.SQLtext = query;

        logger.info("\nbefore\n {} \nafter\n {}", SQLtext, this.SQLtext);
    }
}