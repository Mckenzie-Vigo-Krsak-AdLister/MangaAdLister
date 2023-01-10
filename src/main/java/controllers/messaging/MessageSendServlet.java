package controllers.messaging;

import dao.DaoFactory;
import models.Listing;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="MessageSendServlet", urlPatterns = "/send")
public class MessageSendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int listing_id = Integer.parseInt(req.getParameter("id"));
            User fromUser = (User) req.getSession().getAttribute("loggedInUser");
            Listing currentListing = DaoFactory.getListingsDao().getListingById(listing_id);
            User toUser = DaoFactory.getUsersDao().getUserById((int) currentListing.getUserId());

        } catch (Exception e) {
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}
