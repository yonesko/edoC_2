package server.gleb;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcessSQL {
    public static String substituteParams(String SQL) {
        Scanner in = new Scanner(SQL);
        String query = "";
        Map<String, String> parToVal = new HashMap<String, String>();

        //read query
        String s;
        while (in.hasNext() && !(s=in.nextLine()).contains("Query parameters")) {
            query += s + '\n';
        }
        if (query.length() == 0)
            return "Empty query";
        //read params
        String e[], line;
        while (in.hasNext()) {
            line = in.nextLine();
            if (line.isEmpty() == false) {
                e = line.split("\\s+");
                parToVal.put(e[0], e[1]);
            }
        }
        if (parToVal.size() == 0)
            return "Empty parameters";
        //substitute params in query with values
        String val;
        for (Map.Entry<String, String> entry : parToVal.entrySet()) {
            val = entry.getValue();
            if (val != null && entry.getKey() != null) {
                if (val.equals("null") == false)
                    val = '\'' + val + '\'';
                query = query.replaceAll(entry.getKey(), val);
            }
        }
        //show result
        return query;
    }
}