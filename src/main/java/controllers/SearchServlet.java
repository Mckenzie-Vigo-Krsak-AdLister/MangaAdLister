package controllers;

import dao.DaoFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    protected void doSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("mangas", DaoFactory.getMangaDao().all());
        request.getRequestDispatcher("listings.jsp").forward(request, response);
    }
}
