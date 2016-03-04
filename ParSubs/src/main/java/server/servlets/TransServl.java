package server.servlets;


import server.gleb.ProcessSQL;
import server.templater.MyGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TransServl extends HttpServlet {
    static int i = 0;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(i++);
        String resultSQL = new String();
        String page = new String();
        Map<String, Object> mSQLParams = new HashMap<String, Object>();
        //do work
        try {
            resultSQL = ProcessSQL.substituteParams(req.getParameter("SQLtext"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //proccess template
        mSQLParams.put("resultSQL", resultSQL);
        page = MyGenerator.getInstance().getPage("result.html", mSQLParams);
        //send result of work
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(page);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
