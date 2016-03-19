package servlets;

import accounts.UserProfile;
import dbservice.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService accountService;

    public SignInServlet(DBService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        UserProfile user;

        user = this.accountService.getUserByLogin(login);

        if(user == null || user.getPass() == null || !user.getPass().equals(pass)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        this.accountService.addSession(req.getSession().getId(), user);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Authorized");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
