package dao;

import models.Listing;

import java.sql.SQLException;

public interface ListingsDao {

    public boolean createListing(Listing newlisting);

    public Listing[] allListings();

    public boolean updateListing(long id, Listing updatedListing);

    public boolean deleteListing(long id);

    public Listing getListingById(long id) throws SQLException;

}
