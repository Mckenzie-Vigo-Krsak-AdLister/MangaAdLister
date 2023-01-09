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
//            long id = (long) req.getAttribute("id");
            long id = Long.parseLong(req.getParameter("id"));
            Listing l = DaoFactory.getListingsDao().getListingById(id);
            System.out.println(l.getDescription());
            req.setAttribute("listing", l);

            req.getRequestDispatcher("/WEB-INF/listing.jsp").forward(req,resp);
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
