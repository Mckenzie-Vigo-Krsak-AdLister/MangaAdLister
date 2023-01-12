package controllers.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import models.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveFromCart", urlPatterns = "/removeFromCart")
public class RemoveFromCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DeleteCartItemRequest delreq = mapper.readValue(req.getInputStream(),DeleteCartItemRequest.class);
            System.out.println(delreq.getItem().getId());
            DaoFactory.getCartItemsDao().removeItemById(delreq.getItem().getId());
            res.getWriter().printf("{\"deleted\":\"" + delreq.getItem().getId() + "\"}");
        }catch(Exception e){
            System.out.println("Error while trying to remove an item from the cart.");
            System.out.println(e.getMessage());
        }
    }
}

@JsonIgnoreProperties
class DeleteCartItemRequest {
    private CartItem item;

    public DeleteCartItemRequest(CartItem item){
        this.item = item;
    }
    public DeleteCartItemRequest(){

    }

    public void setItem(CartItem item){
        this.item = item;
    }

    public CartItem getItem() {
        return this.item;
    }

}
