package controllers;

import apis.myanimelist.ApiHandle;
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
import java.util.concurrent.ExecutionException;

@WebServlet(name="ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            //Check if the user is logged in
            try {
                boolean loggedIn = (boolean) req.getSession().getAttribute("loggedIn");
                User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
                Listing l = DaoFactory.getListingsDao().getListingById(loggedInUser.getId());
                //If they are send them to the listing protected route
                if (loggedIn) {
                    req.setAttribute("listing", l);
                    req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
                } else { //Otherwise redirect them to the login page.
                    resp.sendRedirect("/login");
                }
            }catch(NullPointerException e){ //If there's not even an attribute set in the session for loggedIn
                resp.sendRedirect("/login"); //Send them to the login page, because the session is null
            }
        }
        catch (Exception e ){
            try {
                throw e;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        Double price = Double.parseDouble(request.getParameter("price"));

        Manga[] listManga;
        try {
            listManga = DaoFactory.getApiHandle().getMangaContent(title);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Manga manga = new Manga(listManga.title, image, description, price);


    }

}
