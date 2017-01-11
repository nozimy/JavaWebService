package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbservice.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mk-orzu on 08.01.2017.
 */
public class SignUpServlet extends HttpServlet{
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = "";

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null){
            profile = new UserProfile(login, pass, email);
            try {
                accountService.addNewUser(profile);

                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            }catch (DBException e){
                e.printStackTrace();

                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            //return;
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
