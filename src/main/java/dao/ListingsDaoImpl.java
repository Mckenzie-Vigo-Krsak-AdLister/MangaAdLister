package dao;

import com.mysql.cj.jdbc.Driver;
import Config.Config;
import models.Listing;

import java.sql.*;

public class ListingsDaoImpl implements ListingsDao {

    private Connection connection = null;

    public ListingsDaoImpl() {
        try {

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);

        } catch (Exception e) {
            throw new RuntimeException("DataBase Error", e);
        }

    }


    @Override
    public boolean createListing(Listing newlisting) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO manga_adlister.listing(title, image, description, price, users_id) VALUES(?, ?, ?, ?, ?);");
            stmt.setString(1, newlisting.getTitle());
            stmt.setString(2, newlisting.getImage());
            stmt.setString(3, newlisting.getDescription());
            stmt.setDouble(4, newlisting.getPrice());
            stmt.setLong(5, newlisting.getUserId());
            int numberRows = stmt.executeUpdate();
            return numberRows >= 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Listing[] allListings() {
        return new Listing[0];
    }

    @Override
    public boolean updateListing(long id, Listing updatedListing) {
        return false;
    }

    @Override
    public boolean deleteListing(long id) {
        return false;
    }

    @Override
    public Listing getListingById(int id) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM listing WHERE id = ?;");
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int userId = (int) rs.getLong("users_id");

//                System.out.println("Getting Listing By id " + id);
//                System.out.println("Title: " + title);
//                System.out.println("Description: " + description);
//                System.out.println("Image: " + image);
//                System.out.println("Price: " + price);
//                System.out.println("Id:" + id);
//                System.out.println("UserId: " + userId);

                Listing newListing = new Listing(title, description, image, price, id, userId);
                return newListing;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Breaking at ListingsDaoImpl:74");
            throw new RuntimeException(e);
        }
    }
}
