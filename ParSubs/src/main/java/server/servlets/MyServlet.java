package server.servlets;


import server.gleb.ParSubs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet {
    static int i = 0;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        System.out.println(i++);
        try {
            resp.getWriter().println(ParSubs.subs(req.getParameter("SQLtext")));
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
