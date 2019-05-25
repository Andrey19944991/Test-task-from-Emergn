package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    private final AccountService accountService;


    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\"/>");
        pw.println("<title>Registration form</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h1>Registration form!</h1>");
        pw.println("<p>");
        pw.println("<form action=\"/api/v1/users\" method=\"POST\">");
        pw.println("Email: <input type=\"text\" name=\"email\"/>");
        pw.println("Login: <input type=\"text\" name=\"login\"/>");
        pw.println("Password: <input type=\"text\" name=\"pass\"/>");
        pw.println("Password verification: <input type=\"text\" name=\"passValid\"/>");
        pw.println("<input type=\"submit\" value=\"Sign in\">");
        pw.println("</form>");
        pw.println("</p>");
        pw.println("</body>");
        pw.println("</html>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String passValid = request.getParameter("passValid");
        String email = request.getParameter("email");

        if(login==""||pass==""||email==""||!pass.equals(passValid)){
            doGet(request,response);
        }
        else {
            accountService.addNewUser(new UserProfile(login,pass,email));
            PrintWriter pw = response.getWriter();
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset=\"UTF-8\"/>");
            pw.println("<title>Start page</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h1>Start page !</h1>");
            pw.println("<p>");
            //pw.println("<a href=\"page.html\">Change profile</a>");
            pw.println("<form action=\"/change_profile\" method=\"GET\">");
            pw.println("<input type=\"submit\" value=\"Change\">");;
            pw.println("</form>");
            pw.println("</p>");
            //pw.println("<input type=");
            //pw.println("</form>");
            //pw.println("</p>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }


    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {


    }


    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}
