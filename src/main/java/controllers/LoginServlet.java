package controllers;

import dao.UsersDao;
import dao.UsersDaoImpl;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Get the email and password from the request
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Display the incoming login request on the terminal
            System.out.println("Incoming login request.");
            System.out.println("Email:" + email);
            System.out.println("Password:" + password);

            //Use the UsersDao to retrieve a user with that email
            UsersDao usersDao = new UsersDaoImpl();
            User matchingUser = usersDao.getUserByEmail(email);

            //Get the matching user's password
            String userPass = matchingUser.getPassword();

            //Use an instance of BCrypt to check if the passwords match up
            Boolean checksout = BCrypt.checkpw(password,userPass);

            //If it checks out
            if(checksout){
                //Save the loggedIn attribute into the request's session
                request.getSession().setAttribute("loggedIn",true);

                //Use the request dispatcher to send the user to the listings page
                request.getRequestDispatcher("/listings").forward(request,response);
            }else{
                //Set the request parameter for error with a message letting the
                //user know that there was an error authenticating their input
                request.setAttribute("error","There was an error authenticating you. Please try again.");

                //send the user back to the login page
                response.sendRedirect("/login");
            }

        } catch (SQLException | ServletException e){
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e){
            throw e;
        }
    }
}
