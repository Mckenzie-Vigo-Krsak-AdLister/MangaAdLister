package controllers.cart;

import models.Manga;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AddToCartServlet", urlPatterns = "/addtocart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            Manga manga = (Manga) req.getAttribute("manga");
            System.out.println(manga.getTitle_ov());

        }catch(Exception e){
            System.out.println("Error during post request at CartServlet");
            System.out.println(e.getMessage());
        }
    }
}
