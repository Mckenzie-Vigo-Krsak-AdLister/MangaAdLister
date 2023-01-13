package dao;

import Config.Config;
import apis.myanimelist.ApiHandle;
import apis.myanimelist.ApiHandleImpl;
import models.Manga;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class DaoFactory {
    private static Mangas mangasDao;
    private static UsersDao usersDao;
    private static ListingsDao listingsDao;
    private static RecoveryDao recoveryDao;
    private static ProfileDao profileDao;
    private static CartDao cartDao;
    private static ApiHandle apiHandle;
    public static ApiHandle getApiHandle() {
        if (apiHandle == null) {
            apiHandle = new ApiHandleImpl();
        }
        return apiHandle;
    }
    private static SearchDao searchDao;
    private static CartItemDao cartItemDao;
    public static Mangas getMangaDao() {
        if (mangasDao == null) {
            mangasDao = new MySQLMangaDao();
        }
        return mangasDao;
    }
    public static UsersDao getUsersDao() {
        if (usersDao == null) {
            usersDao = new UsersDaoImpl();
        }
        return usersDao;
    }
    public static ListingsDao getListingsDao() {
        if (listingsDao == null) {
            listingsDao = new ListingsDaoImpl();
        }
        return listingsDao;
    }
    public static RecoveryDao getRecoveryDao() throws SQLException {
        if(recoveryDao == null){
            recoveryDao = new RecoveryDaoImpl();
        }
        return recoveryDao;
    }
    public static CartDao getCartDao() throws SQLException {
        if(cartDao == null){
            cartDao = new CartDaoImpl();
        }
        return cartDao;
    }
    public static SearchDao getSearchDao() throws SQLException {
        if (searchDao == null) {
            searchDao = new SearchDao();
        }
        return searchDao;
    }
    public static CartItemDao getCartItemsDao () throws SQLException {
        if (cartItemDao == null) {
            cartItemDao = new CartItemDaoImpl();
        }
        return cartItemDao;
    }

}

