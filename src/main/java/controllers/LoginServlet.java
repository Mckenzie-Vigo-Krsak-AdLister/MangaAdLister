package controllers;

import dao.DaoFactory;
import dao.UsersDao;
import dao.UsersDaoImpl;
import models.Cart;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(name="Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //If theres a session variable for a logged in user
            if(request.getSession().getAttribute("loggedIn") != null) {
                //Grab it from the session
                boolean loggedIn = (boolean) request.getSession().getAttribute("loggedIn");
                if (loggedIn) { //Check if they are logged in
                    System.out.println("Login servlet executing, user logged in: " + loggedIn);
                    //Redirect to the listings page
                    request.getRequestDispatcher("/WEB-INF/listings.jsp").forward(request, response);
                }
            }

            //Otheriwse just go to login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }catch(Exception e){  //Session is null
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

            if(matchingUser != null) {
                //Get the matching user's password
                String userPass = matchingUser.getPassword();

                //Use an instance of BCrypt to check if the passwords match up
                Boolean checksout = BCrypt.checkpw(password, userPass);

                //If it checks out
                if (checksout) {
                    //Save the loggedIn attribute into the request's session
                    request.getSession().setAttribute("loggedIn", true);
                    request.getSession().setAttribute("loggedInUser",matchingUser);

                    if(DaoFactory.getCartDao().getLatestCartForUser(matchingUser) != null) {
                        //Pull the latest cart
                        Cart latestCart = DaoFactory.getCartDao().getLatestCartForUser(matchingUser);

                        //Save the latest cart in a session variable
                        request.getSession().setAttribute("cart", latestCart);
                    }

                    //Use the request dispatcher to send the user to the listings page
                    //                request.getRequestDispatcher("/listings").forward(request,response);
                    response.sendRedirect("/listings");
                } else {
                    //send the user back to the login page
                    response.sendRedirect("/login?error=authfailed");
                }
            }else{
                response.sendRedirect("/login?error=notfound");
            }
        } catch (SQLException e){
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
