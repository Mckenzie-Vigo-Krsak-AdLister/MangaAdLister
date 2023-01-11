package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import models.Listing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet (name = "searchFunc", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        ObjectMapper  mapper = new ObjectMapper();
        SearchRequest req = mapper.readValue(request.getInputStream(), SearchRequest.class);
        System.out.println(req.getSearchTerm());
//        try {
//            String title = request.getParameter("searchTerm");
//            List<Listing> results = DaoFactory.getSearchDao().getListingsByTitle(title);
//            System.out.println(results.size());
//            for (Listing l: results){
//                System.out.println(l.getTitle());
//            }
//            request.setAttribute("results", results);
//            response.setContentType("application/json");
//            String resultsJson = mapper.writeValueAsString(results);
//            response.getWriter().println(resultsJson);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        request.setAttribute("mangas", DaoFactory.getMangaDao().all());
//        request.getRequestDispatcher("listings.jsp").forward(request, response);
    }


}

class SearchRequest {
    private String searchTerm;

    public SearchRequest(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchRequest() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
