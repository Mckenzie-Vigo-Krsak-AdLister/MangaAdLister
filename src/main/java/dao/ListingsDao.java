package dao;

import models.Listing;

import java.sql.SQLException;
import java.util.List;

public interface ListingsDao {

    public boolean createListing(Listing newlisting);

    public Listing[] allListings();

    public boolean updateListing(long id, Listing updatedListing);

    public boolean deleteListing(long id);

    public Listing getListingById(long id) throws SQLException;

    public List<Listing> getListingsByUserId(int id) throws SQLException;

}
