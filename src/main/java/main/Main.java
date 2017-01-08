package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SimpleServlet;

/**
 * Created by mk-orzu on 07.01.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        SimpleServlet simpleServlet = new SimpleServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(simpleServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();

    }



}
