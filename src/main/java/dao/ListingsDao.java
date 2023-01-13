package dao;

import models.Listing;

import java.sql.SQLException;
import java.util.List;

public interface ListingsDao {

    public boolean createListing(Listing newlisting);
    public List<Listing> allListings();
    public boolean updateListing(long id, Listing updatedListing);
    public boolean deleteListing(int userId, int listingId);
    public Listing getListingById(int id) throws SQLException;
    public List<Listing> getListingsByUserId(int id) throws SQLException;

}
