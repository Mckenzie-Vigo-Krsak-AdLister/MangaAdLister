package dao;

import apis.myanimelist.ApiHandle;
import models.Listing;
import models.Manga;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface ProfileDao {

    public boolean createListing(Listing newlisting);
    public Listing[] allListings();
    public boolean updateListing(long id, Listing updatedListing);
    public boolean deleteListing(long id);
    public Listing getListingById(long id) throws SQLException;

}
