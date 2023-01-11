package dao;

import Config.Config;

import java.sql.SQLException;

public class DaoFactory {
    private static Mangas mangasDao;
    private static UsersDao usersDao;
    private static ListingsDao listingsDao;

    private static RecoveryDao recoveryDao;

    private static CartDao cartDao;

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

}
