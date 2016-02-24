package server.gleb;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.TransServl;
import server.servlets.IziJarDownloaderServl;

import java.io.PrintWriter;

public class Main {
    private static final int PORT_POSITION = 0;
    private static int port;
    public static void main(String...argv) throws Exception {
        TransServl transServl = new TransServl();
        IziJarDownloaderServl scriptServlet = new IziJarDownloaderServl();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceHandler resource_handler = new ResourceHandler();
        //map servlets
        context.addServlet(new ServletHolder(transServl), "/trans");
        context.addServlet(new ServletHolder(scriptServlet), "/IziJar.bash");

        resource_handler.setResourceBase("public_html");
        //add servlets to Jetty
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        port = Integer.parseInt(argv[PORT_POSITION]);
        Server server = new Server(port);
        server.setHandler(handlers);
        //start server
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
