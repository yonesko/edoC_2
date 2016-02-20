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
    public static void main(String...args) throws Exception {
        TransServl transServl = new TransServl();
        IziJarDownloaderServl scriptServlet = new IziJarDownloaderServl();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceHandler resource_handler = new ResourceHandler();

        context.addServlet(new ServletHolder(transServl), "/trans");
        context.addServlet(new ServletHolder(scriptServlet), "/IziJar.bash");

        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8081);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
