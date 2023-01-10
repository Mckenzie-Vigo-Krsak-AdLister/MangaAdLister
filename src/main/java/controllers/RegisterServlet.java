package controllers;

import dao.DaoFactory;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name="Register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getSession().getAttribute("loggedIn") != null) {
                Boolean loggedIn = Boolean.getBoolean(request.getSession().getAttribute("loggedIn").toString());
                if (loggedIn) {
                    request.getRequestDispatcher("/WEB-INF/listings.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }else{
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean userDoesNotExist(String email) throws SQLException {
        User usr = DaoFactory.getUsersDao().getUserByEmail(email);
        if(usr != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String accountType = request.getParameter("accountType");

            System.out.println("Incoming login request.");
            System.out.println("Email:" + email);
            System.out.println("Password:" + password);
            System.out.println("Confirm:" + confirm);
            System.out.println("Name:" + name);
            System.out.println("Last Name:" + lastName);
            System.out.println("Account Type" + accountType);

            if(!password.equals(confirm)){
                response.sendRedirect("/register?error=passwordmismatch");
            }else{

                if(this.userDoesNotExist(email)) {

                    Date now = new Date();
                    String roles = "USER";
                    if (accountType.equals("admin")) {
                        roles = "ADMIN";
                    } else if (accountType.equals("user")) {
                        roles = "USER";
                    } else {
                        //Dont even think about it
                        System.out.println("Fishy activity detected in the Registration department. Please check logs for baffonery.");
                    }

                    User newUser = new User(email, password,name,lastName, now, roles);
                    long newUserId = DaoFactory.getUsersDao().addUser(newUser);

                    request.getSession().setAttribute("loggedIn", true);
                    request.getSession().setAttribute("uid", newUserId);
//                    request.getRequestDispatcher("/listings.jsp").forward(request, response);
                    response.sendRedirect("/listings");
                }else{
                    request.getSession().invalidate();
                    response.sendRedirect("/register?error=accountexists");
                }
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}