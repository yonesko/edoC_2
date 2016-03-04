package server.gleb;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcessSQL {
    static final String SECTION_DELIMITER = "Query parameters";
    public static String substituteParams(String SQL) throws Exception {
        Scanner in = new Scanner(SQL);
        String query = new String();
        Map<String, String> mParToVal = new HashMap<String, String>();

        //read query
        String line;
        while (in.hasNext() && !(line=in.nextLine()).contains(SECTION_DELIMITER))
            if (!line.matches("\\s*"))
                query += line + '\n';

        if (query.length() == 0)
            return "Empty query";
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
            return String.format("Empty parameters or absent \"%s\" string", SECTION_DELIMITER);
        //substitute params in query with values
        String val;
        for (Map.Entry<String, String> entry : mParToVal.entrySet()) {
            val = entry.getValue();
            if (val != null && entry.getKey() != null) {
                if (val.equals("null") == false)
                    val = '\'' + val + '\'';
                query = query.replaceAll(entry.getKey(), val);
            }
        }
        //show result
        try {
            query = formatSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }
    private static String formatSQL(String query) throws Exception {
        String url = "http://sqlformat.org/api/v1/format";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "keyword_case=upper&reindent=1&sql="+query;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        if (con.getResponseCode() != 200)
            throw new Exception("sqlformat.org query not OK");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        JSONObject jResult = new JSONObject(response.toString());
        return jResult.getString("result");
    }
}
