package server.gleb;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.TransServl;
import server.servlets.IziJarDownloaderServl;

public class Main {
    private static final int PORT_POSITION = 0;
    public static void main(String...argv) throws Exception {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        TransServl transServl = new TransServl();
        IziJarDownloaderServl scriptServlet = new IziJarDownloaderServl();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceHandler resource_handler = new ResourceHandler();

        context.addServlet(new ServletHolder(transServl), "/trans");
        context.addServlet(new ServletHolder(scriptServlet), "/IziJar.bash");

        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(Integer.parseInt(argv[Main.PORT_POSITION]));
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
