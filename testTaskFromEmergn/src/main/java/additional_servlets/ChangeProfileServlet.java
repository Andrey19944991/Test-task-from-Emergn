package additional_servlets;

import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class ChangeProfileServlet extends HttpServlet {
    private final AccountService accountService;
    public Stack<String> list;

    public ChangeProfileServlet(AccountService accountService,Stack<String> list) {
        this.accountService = accountService;
        this.list=list;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        //НЕ УСПЕЛ ДОДЕЛАТЬ
        String login=accountService.getUserByLogin(list.peek()).getLogin();
        String email=accountService.getUserByLogin(list.peek()).getEmail();
        String pass=accountService.getUserByLogin(list.peek()).getPass();
        PrintWriter pw=response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\"/>");
        pw.println("</head>");
        pw.println("<form action=\"/change_profile\" method=\"POST\">");
        pw.println("Email: <input type=\"text\" name=\"email\"/>");
        pw.println("Login: <input type=\"text\" name=\"login\"/>");
        //pw.println("");
        pw.println("Password: <input type=\"text\" name=\"pass\"/>");
        pw.println("<input type=\"submit\" value=\"Change\">");
        pw.println("</form>");
        pw.println("</p>");
        pw.println("</body>");
        pw.println("</html>");

    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{

    }
}
