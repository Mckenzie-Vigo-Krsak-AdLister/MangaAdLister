package controllers;

import dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListingsServlet", urlPatterns = "/listings")
public class ListingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
// Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        request.setAttribute("mangas", DaoFactory.getMangaDao().all());
        request.getRequestDispatcher("listings.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        boolean loggedIn = (boolean) request.getSession().getAttribute("loggedIn");
//        if(loggedIn)
    }
}



