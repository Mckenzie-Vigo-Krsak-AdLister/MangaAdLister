package controllers.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCartItemQty", urlPatterns= "/updateCartItemQty")
public class UpdateCartItemQty extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            
        }catch(Exception e){
            System.out.println("There was an error while updating the quantity of a cart item.");
            System.out.println(e.getMessage());
        }
    }
}
