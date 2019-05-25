package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class SessionsServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionsServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        UserProfile profile = accountService.getUserByLogin(login);

        if(login == null || pass == null||profile == null || !profile.getPass().equals(pass)){
            PrintWriter pw=response.getWriter();
            pw.println("<html>"
                    +"<head> "
                    +"<meta charset=\"UTF-8\"/> "
                    +"</head>"
                    +"<body>"
                    +"<h3>Invalid input</h3>"
                    +"<p><a href=\"http://localhost:8080\">Repeat!</a></p>"
                    +"</body> "
                    +"</html>");
        }
        else {
            PrintWriter pw = response.getWriter();
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset=\"UTF-8\"/>");
            pw.println("<title>Start page</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h1>Start page !</h1>");
            pw.println("<p>");
            pw.println("<form action=\"/change_profile\" method=\"GET\">");
            pw.println("<input type=\"submit\" value=\"Change profile\">");
            pw.println("</form>");
            pw.println("</p>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }



    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);

        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    private void returnStartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        pw.println("<!DOCTYPE html>\n <html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <title>Web Portal</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Web portal</p>\n" +
                "\n" +
                "<form action=\"/api/v1/sessions\" method=\"POST\">\n" +
                "    Login: <input type=\"text\" name=\"login\"/>\n" +
                "    Password: <input type=\"password\" name=\"pass\"/>\n" +
                "    <input type=\"submit\" value=\"Sign in\">\n" +
                "</form>\n" +
                "<p>\n" +
                "<form action=\"/api/v1/users\" method=\"GET\">\n" +
                "    <input type=\"submit\" value=\"Sign up\">\n" +
                "</form>\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>");
    }
}

