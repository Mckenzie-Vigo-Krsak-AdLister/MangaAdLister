package controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoFactory;
import models.Listing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListingsServlet", urlPatterns = "/listings")
public class ListingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
// Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        request.setAttribute("mangas", DaoFactory.getMangaDao().all());
        request.getRequestDispatcher("listings.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("searchInput"));
        System.out.println("log");
        try {
            ObjectMapper mapper = new ObjectMapper();
            GetListingsRequest req = mapper.readValue(request.getInputStream(), GetListingsRequest.class);
            System.out.println("request: " + req.getUserId());
//            User loggedInUser = (User) req.getSession().getAttribute("user");
//            System.out.println(loggedInUser.getFirstName());
            List<Listing> listings = DaoFactory.getListingsDao().allListings();

            response.setContentType("application/json");

            String listingsJson = mapper.writeValueAsString(listings);

            response.getWriter().println(listingsJson);

        } catch (Exception e) {
            throw e;
        }

    }

}

@JsonIgnoreProperties
class GetListingsRequest {
    private int userId;

    public GetListingsRequest() {
    }

    public GetListingsRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}

