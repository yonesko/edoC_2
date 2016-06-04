package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;
import java.util.concurrent.TimeUnit;


@WebServlet(name = "WebSocketAuthServlet", urlPatterns = {"/chat"})
public class WebSocketAuthServlet extends WebSocketServlet {
    private final static long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);
    private final AuthService authService;

    public WebSocketAuthServlet() {
        this.authService = new AuthService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new AuthWebSocket(authService));
    }
}
