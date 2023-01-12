package controllers.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

@WebServlet (name = "GetProfileListings", urlPatterns = "/profilelistings")
public class GetProfileListings extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            GetProfileListingsRequest request = mapper.readValue(req.getInputStream(), GetProfileListingsRequest.class);
            System.out.println("request: " + request.getUserId());
//            User loggedInUser = (User) req.getSession().getAttribute("user");
//            System.out.println(loggedInUser.getFirstName());
            List<Listing> listings = DaoFactory.getListingsDao().getListingsByUserId(request.getUserId());

            resp.setContentType("application/json");

            String listingsJson = mapper.writeValueAsString(listings);

            resp.getWriter().println(listingsJson);

        } catch (Exception e) {
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}

@JsonIgnoreProperties
class GetProfileListingsRequest {
    private int userId;

    public GetProfileListingsRequest() {
    }

    public GetProfileListingsRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}