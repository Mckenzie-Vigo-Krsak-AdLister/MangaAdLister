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
    public Listing getListingById(long id) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manga_adlister.listing WHERE id = ? LIMIT 1;");
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            String title = rs.getString("title");
            String image = rs.getString("image");
            String description = rs.getString("description");
            Double price = rs.getDouble("price");
            long userId = rs.getLong("users_id");
            Listing newListing = new Listing(title, description, image, price, id, userId);
            return newListing;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Listing[] getListingsByUserId(long userId) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manga_adlister.listing WHERE users_id = ?;");
            stmt.setLong(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            Listing[] listings = new Listing[0];
            while (rs.next()) {
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                long id = rs.getLong("id");
                Listing newListing = new Listing(title, description, image, price, id, userId);
                listings = allListings();
            }
            return listings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
