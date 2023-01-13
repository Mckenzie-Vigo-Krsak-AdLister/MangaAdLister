package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.Listing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    private Connection connection = null;
    public SearchDao() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.jdbcConnectionString,
                    Config.mysqlUser,
                    Config.mysqlPassword
            );

        } catch (SQLException e) {
            throw new RuntimeException("DataBase Error", e);
        }

    }

    public List<Listing> getListingsByTitle(String searchTitle) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("Select * from listing where title like ?;");
            stmt.setString(1, "%" + searchTitle + "%");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            List<Listing> searchListing = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int userId = rs.getInt("users_id");
                Listing newListing = new Listing(title, description, image, price, id, userId);
                searchListing.add(newListing);
            }
            return searchListing;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}