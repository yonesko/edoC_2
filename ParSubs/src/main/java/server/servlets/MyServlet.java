package server.servlets;


import server.gleb.ParSubs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        try {
            resp.getWriter().println(ParSubs.subs(req.getParameter("SQLtext")));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
