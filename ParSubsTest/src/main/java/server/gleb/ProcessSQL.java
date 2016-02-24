package server.gleb;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ProcessSQL {
    public static String substituteParams(String SQL) throws IOException {
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
        return formatSQL(query);
    }
    private static String formatSQL(String query) throws IOException {
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

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("sqlformat.org query not OK");
            return query;
        }
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);

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
