package controllers.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteListingServlet", urlPatterns = "/deletelisting")
public class DeleteListingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Im trying to work");
        // get the title and user_id from request
//        int listingId = Integer.parseInt(request.getParameter("listingId"));
//        int userId = Integer.parseInt(request.getParameter("user_id"));
        ObjectMapper mapper = new ObjectMapper();
        DeleteListingRequest req = mapper.readValue(request.getInputStream(), DeleteListingRequest.class);
        // delete the listing from the database
        DaoFactory.getListingsDao().deleteListing(req.getUserId(), req.getListingId());
        System.out.println("listing deleted");
        response.getWriter().println("{}");


    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class DeleteListingRequest {

    private int userId;

    private int listingId;

    public int getUserId() {
        return userId;
    }

    public int getListingId() {
        return this.listingId;
    }

    public DeleteListingRequest(){

    }

    public DeleteListingRequest(int userId, int listingId) {
        this.userId = userId;
        this.listingId = listingId;
    }



}
