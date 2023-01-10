package controllers;

import dao.DaoFactory;
import models.Listing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="ListingServlet", urlPatterns = "/listing")
public class ListingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            Listing l = DaoFactory.getListingsDao().getListingById(id);

            //Check if the user is logged in
            try {
                boolean loggedIn = (boolean) req.getSession().getAttribute("loggedIn");

                //If they are send them to the listing protected route
                if (loggedIn) {
                    req.setAttribute("listing", l);
                    req.getRequestDispatcher("/WEB-INF/listing.jsp").forward(req, resp);
                } else { //Otherwise redirect them to the login page.
                    resp.sendRedirect("/login");
                }
            }catch(NullPointerException e){ //If there's not even an attribute set in the session for loggedIn
                resp.sendRedirect("/login"); //Send them to the login page, because the session is null
            }
        }
        catch (Exception e ){
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
