package dao;

import models.Listing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    private Connection connection = null;

    public List<Listing> getListingsByTitle(String searchTitle) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("Select * from listing where title  rlike ?");
            stmt.setString(1, "%" + searchTitle + "%");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            List<Listing> searchListing = new ArrayList<>();
            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                long userId = rs.getLong("users_id");
                Listing newListing = new Listing(title, description, image, price, id, userId);
                searchListing.add(newListing);
            }
            return searchListing;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
