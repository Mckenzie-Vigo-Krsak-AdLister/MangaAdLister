package controllers;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import dao.DaoFactory;
import models.User;
import services.EmailService;
import services.EmailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Recover", urlPatterns = "/recover")
public class RecoverServlet extends HttpServlet {

    Connection connection;

    public RecoverServlet() throws SQLException {
        try{
            try{
                DriverManager.registerDriver(new Driver());
                connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);
            }catch(Exception e){
                throw e;
            }
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            req.getRequestDispatcher("/WEB-INF/recover.jsp").forward(req,res);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            String email = req.getParameter("email");

            EmailService emailService = new EmailServiceImpl();
            User userForEmail = DaoFactory.getUsersDao().getUserByEmail(email);
            if(userForEmail != null) {
                String domain = "<a href='http://localhost:8080/recover";
                StringBuilder returnUrl = new StringBuilder();
                returnUrl.append(domain);
                try {
                    SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
                    String randomNum = String.valueOf(prng.nextInt());

                    //Get an instance of sha1 and get digest a bunch of random bytes
                    MessageDigest sha = MessageDigest.getInstance("SHA-1");
                    byte[] result = sha.digest(randomNum.getBytes());

                    //Generate the nonce with StringBuilder
                    StringBuilder nonce = new StringBuilder();

                    //HEX Digits
                    char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                            'a', 'b', 'c', 'd', 'e', 'f' };

                    //For each byte
                    for (int idx = 0; idx < result.length; ++idx) {
                        byte b = result[idx];
                        nonce.append(digits[(b & 0xf0) >> 4]); //Do some binary manip currentByte AND 0xf0 SHIFTRIGHT 4
                        nonce.append(digits[b & 0x0f]); //Do some binary manip currentByte AND 0xf0
                    }

                    //Attach the nonce to the domain
                    returnUrl.append("?return=true&nonce=");
                    returnUrl.append(nonce);
                    returnUrl.append("'><button>Recover my MangaLister Password Meow!</button></a>");

                    // Store the nonce into the recovery table using the Recovery Dao
                    PreparedStatement stmt = connection.prepareStatement("INSERT INTO recovery(code,userid) VALUES(?,?);");
                    stmt.setString(1,nonce.toString());
                    stmt.setInt(2,userForEmail.getId());
                    stmt.execute();

                    //Send the email out
                    emailService.sendEmail(userForEmail,"Password Recovery Link",returnUrl.toString());

                    //redirect back to th recover servlet with a message
                    res.sendRedirect("/recover?sent=true");
                } catch (Exception e) {
                    System.out.println("ERROR FOUND IN RECOVER SERVLET");
                    System.out.println(e.getMessage());
                    throw e;
                }
            }else{
                res.sendRedirect("/recover?error=notfound");
            }
        }catch(Exception e){
            try {
                System.out.println(e.getMessage());
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
