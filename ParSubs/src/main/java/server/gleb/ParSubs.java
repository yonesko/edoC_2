package server.gleb;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParSubs {
    public static String subs(String SQL) throws Exception {
        Scanner in = new Scanner(SQL);
        String query = "";
        Map<String, String> parToVal = new HashMap<String, String>();

        //read query
        String s;
        while (in.hasNext() && !(s=in.nextLine()).contains("Query parameters")) {
            query += s + '\n';
        }
        if (query.length() == 0)
            throw new Exception("Empty query");
        //read params
        String e[];
        while (in.hasNext()) {
            e = in.nextLine().split("\\s+");
            parToVal.put(e[0], e[1]);
        }
        if (parToVal.size() == 0)
            throw new Exception("Empty parameters");
        //substitute params in query with values
        String val;
        for (Map.Entry<String, String> entry : parToVal.entrySet()) {
            val = entry.getValue();
            if (val.equals("null") == false)
                val = '\'' + val + '\'';
            query = query.replaceAll(entry.getKey(), val);
        }
        //show rsult
        return query;
    }
}
