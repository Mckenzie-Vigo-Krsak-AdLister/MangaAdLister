package controllers.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CartDao;
import dao.CartItemDao;
import dao.DaoFactory;
import models.*;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet(name="AddToCartServlet", urlPatterns = "/addtocart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            //Create an object mapper
            ObjectMapper mapper = new ObjectMapper();

            //Map the request's input stream to an AddToCartRequest object
            AddToCartRequest response = mapper.readValue(req.getInputStream(), AddToCartRequest.class);

            //Grab the logged-in user
            User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");

            //Show message in terminal for debugging purposes
            System.out.println(loggedInUser.getEmail() + " adding manga with id " + response.getListingId() + " to their cart.");

            //Use the DaoFactory to get the listing
            Listing listing = DaoFactory.getListingsDao().getListingById(response.getListingId());

            //Use the DaoFactory to get the CartDao
            CartDao cartDao = DaoFactory.getCartDao();

            //Use the cart dao to add the listing to the user's cart
            Cart latestUsersCart = cartDao.getLatestCartForUser(loggedInUser);

            Integer latestUsersCartId = null;
            if(latestUsersCart != null) {
                //Get the id of the latest cart
                latestUsersCartId = (Integer) latestUsersCart.getId();

            }else{
                //Create a new cart
                latestUsersCartId = cartDao.createCartForUser(loggedInUser.getId());
            }

            //Get the CartItemDao from the DaoFactory
            CartItemDao cartItemDao = DaoFactory.getCartItemsDao();

            //Sanity Check
            System.out.println(loggedInUser.getId());
            System.out.println(latestUsersCartId);
            System.out.println(response.getListingId());

            //Create a cart item
            CartItem newCartItem = new CartItem(
                    loggedInUser.getId(),
                    response.getListingId(),
                    latestUsersCartId
            );

            //Use the cart item dao to add the item to the latest user's cart
            cartItemDao.addCartItem(newCartItem);

            //Get all the cart itmes for the user
            List<CartItem> cartItems = DaoFactory.getCartItemsDao().getCartItemsForUser(loggedInUser.getId());

            //Get the session and add the cart items to it
            req.getSession().setAttribute("cart", cartItems);

            //Use the object mapper to turn the cart items into a json list
            String cartItemsJson = mapper.writeValueAsString(cartItems);

            //Send a json response back to the client
            res.setContentType("application/json");
            PrintWriter w = res.getWriter();
            w.printf(cartItemsJson);

        }catch(Exception e){
            System.out.println("Error during post request at CartServlet");
            System.out.println(e.getMessage());
        }
    }
}

class AddToCartRequest {
    private int listingId;
    public int getListingId(){
        return this.listingId;
    }
    public void setListingId(int id){
        this.listingId = id;
    }
}
