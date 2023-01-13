package controllers.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import models.CartItem;
import models.Listing;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name="CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request,response);
        }catch(Exception e){
            System.out.println("Error while serving the cart JSP from CartServlet.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            //Get the currently logged in user
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

            //Get the list of cart items
            List<CartItem> cartItems = DaoFactory.getCartItemsDao().getCartItemsForUser(loggedInUser.getId());

            if(cartItems != null) {
                //Pull their cart items and return their length
                ObjectMapper mapper = new ObjectMapper();

                List<PopulatedCartItem> populatedCartItems = new ArrayList<>();

                for(CartItem item : cartItems) {

                    Listing listing = DaoFactory.getListingsDao().getListingById(item.getListingId());
                    User owner = DaoFactory.getUsersDao().getUserById((int)listing.getUserId());
                    System.out.println(owner.getFirstName());
                    System.out.println(listing.getTitle());
                    PopulatedCartItem curItem = new PopulatedCartItem(listing, owner, item);
                    populatedCartItems.add(curItem);
                }

                String cartJson = mapper.writeValueAsString(populatedCartItems);
                response.getWriter().println(cartJson);
            }else{
                response.getWriter().printf("\"error\":\"cart items was null\"");
            }

        }catch (Exception e){
            System.out.println("Error while retrieving the cart.");
            System.out.println(e.getMessage());
        }
    }
}


@JsonIgnoreProperties
class PopulatedCartItem {
    private Listing listing;
    private User owner;

    private CartItem item;

    public PopulatedCartItem(Listing listing, User owner, CartItem item){
        this.listing = listing;
        this.owner = owner;
        this.item = item;
    }

    public PopulatedCartItem(){

    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CartItem getItem() {
        return this.item;
    }

    public void setItem(CartItem item){
        this.item = item;
    }

}