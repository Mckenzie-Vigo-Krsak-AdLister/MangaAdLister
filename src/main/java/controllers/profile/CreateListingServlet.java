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

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        CreateListingRequest req = mapper.readValue(request.getInputStream(), CreateListingRequest.class);

        ApiHandle myApi = new ApiHandleImpl();

        try {

            Manga newManga = myApi.getMangaContentById(req.getNewListing().getMyanimelist_id());
            Listing newListing = new Listing(newManga.getTitle_ov(), newManga.getSynopsis(), newManga.getPicture_url(), req.getPrice(), req.getUserId());
            DaoFactory.getListingsDao().createListing(newListing);

            response.getWriter().println("{}");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class CreateListingRequest {

    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    private double price;
    private int userId;
    private Manga newListing;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public CreateListingRequest(Manga newListing , int userId, double price) {
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
