package controllers.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import models.CartItem;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="GetCartSize", urlPatterns = "/getcartsize")
public class GetCartSize extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            //Get the currently logged in user
            User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");

            List<CartItem> cartItems = DaoFactory.getCartItemsDao().getCartItemsForUser(loggedInUser.getId());

            System.out.println(cartItems.size());

            //Pull their cart items and return their length
            res.getWriter().println(cartItems.size());

        }catch (Exception e){
            System.out.println("Error while retrieving the cart size.");
            System.out.println(e.getMessage());
        }
    }
}

@JsonIgnoreProperties
class CartSizeRequest {

}