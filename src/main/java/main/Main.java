package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbservice.DBException;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by mk-orzu on 07.01.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        AccountService accountService = new AccountService();

        try {
            accountService.addNewUser(new UserProfile("admin2"));
            accountService.addNewUser(new UserProfile("test2"));
        }catch (DBException e) {
            e.printStackTrace();
        }
        //SimpleServlet simpleServlet = new SimpleServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();

    }



}
