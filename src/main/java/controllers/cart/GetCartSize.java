package controllers.cart;

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

@WebServlet(name = "GetCartSize", urlPatterns = "/getcartsize")
public class GetCartSize extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        System.out.println("User attempting to get the size of their cart.");
        try{
            User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");

            if(loggedInUser == null){
                System.out.println("loggedInUser not in the session.");
            }else{
                int loggedInUserId = loggedInUser.getId();
                List<CartItem> cartItemsForUser = DaoFactory.getCartItemsDao().getCartItemsForUser(loggedInUserId);
                System.out.println("Cart Size: " + cartItemsForUser.size());
                res.getWriter().printf("{\"cart_size\":\"" + cartItemsForUser.size() + "\"}");
            }
        }catch(Exception e){
            System.out.println("Error while attempting to get the cart size.");
            System.out.println(e.getMessage());
        }
    }
}
