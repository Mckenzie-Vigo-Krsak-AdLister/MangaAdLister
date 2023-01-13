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

        try {
           List<Listing> l =  DaoFactory.getSearchDao().getListingsByTitle(req.getSearchTerm());

           for (Listing listing : l) {
               System.out.println(listing.getTitle());
           }
           String json = mapper.writeValueAsString(l);
              response.setContentType("application/json");
                response.getWriter().write(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
