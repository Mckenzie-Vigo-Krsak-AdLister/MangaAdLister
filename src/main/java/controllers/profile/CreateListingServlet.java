package controllers.profile;

import apis.myanimelist.ApiHandle;
import apis.myanimelist.ApiHandleImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import models.Listing;
import models.Manga;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet (name = "CreateListingServlet", urlPatterns = "/addlisting")
public class CreateListingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ObjectMapper  mapper = new ObjectMapper();
        CreateListingRequest req = mapper.readValue(request.getInputStream(), CreateListingRequest.class);
        System.out.println(req.getNewListing().getMyanimelist_id());

        ApiHandle myApi = new ApiHandleImpl();
        try {
            Manga newManga = myApi.getMangaContentById(req.getNewListing().getMyanimelist_id());

//            ublic Listing(String title, String description, String image, Double price, long userId)

            System.out.println(newManga.getTitle());
            System.out.println(req.getUserId());
//            Listing newListing = new Listing(newManga.getTitle(), newManga.getSynopsis(), newManga.getPicture_url(), 0.0, loggedInUser.getId());

//            DaoFactory.getListingsDao().createListing(newListing);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        boolean results = DaoFactory.getMangaDao().createListing(req.getNewListing());

//        request.setAttribute("listingCreated", results);
//        response.setContentType("application/json");
//        String resultsJson = mapper.writeValueAsString(results);
//        response.getWriter().println(resultsJson);

    }

}
@JsonIgnoreProperties(ignoreUnknown = true)
class CreateListingRequest {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private Manga newListing;
    public CreateListingRequest(Manga newListing , String userId) {
        this.userId = userId;
        this.newListing = newListing;
    }

    public Manga getNewListing() {
        return newListing;
    }

    public void setNewListing(Manga newListing) {
        this.newListing = newListing;
    }

    public CreateListingRequest(){}

}
