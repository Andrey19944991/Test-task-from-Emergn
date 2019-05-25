package main;

import accounts.AccountService;
import accounts.UserProfile;
import additional_servlets.ChangeProfileServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.UsersServlet;

import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        Stack<String> list=new Stack<>();
        AccountService accountService = new AccountService(list);

        accountService.addNewUser(new UserProfile("admin")); //просто так добавил два профиля
        accountService.addNewUser(new UserProfile("test"));
        ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");//чтобы слушал все запросы
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new ChangeProfileServlet(accountService,list)),"/change_profile");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates/index.html");//место статической страницы
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server=new Server(8080);//это Jetty - server и его порт

        server.setHandler(handlers);

        server.start(); // Jetty начнёт создавать пул потоков для обработки запросов
        System.out.println("Server started");
        server.join();
    }
}
