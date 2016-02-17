package servlets;

import templater.MyGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print("ты БРАТИШКА");

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> vars = MyServlet.createPageVariables(req);
        String login = req.getParameter("message");

        if (login == null || login.isEmpty())
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            resp.setStatus(HttpServletResponse.SC_OK);

        vars.put("message", login);

        resp.getWriter().println("ты гавно");
    }

    private static Map<String, Object> createPageVariables(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("method", request.getMethod());
        res.put("URL", request.getRequestURL().toString());
        res.put("pathInfo", request.getPathInfo());
        res.put("sessionId", request.getSession().getId());
        res.put("parameters", request.getParameterMap().toString());

        return res;
    }
}
