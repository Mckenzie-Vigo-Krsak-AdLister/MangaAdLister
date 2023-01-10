package controllers;

import dao.DaoFactory;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="PasswordResetServlet", urlPatterns = "/resetpassword")
public class PasswordResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            String password = req.getParameter("password");
            String password_confirmation = req.getParameter("password_confirmation");
            String nonce = req.getParameter("nonce");

            //Server side validation check
            if(password.equals(password_confirmation) && nonce != null){
                //If the passwords match and there's a nonce available

                //Pull the user's id for the given nonce
                int uid = DaoFactory.getRecoveryDao().getUserIdForCode(nonce);

                //Change the user's password to the new password
                if(DaoFactory.getUsersDao().updateUserPassword(uid,password)){

                }else{

                }
            }else{ //Send them back
                res.sendRedirect("/recover?return=true&nonce=" + nonce);
            }

        }catch(Exception e){
            System.out.println("Error found in resetpassword servlet");
            System.out.println(e.getMessage());
        }
    }
}
