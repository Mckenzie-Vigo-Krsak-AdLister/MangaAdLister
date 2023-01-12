package dao;

import models.Cart;
import models.User;

import java.sql.SQLException;

public interface CartDao {
    public Cart getCartById(int id) throws SQLException;
    public Cart[] getUserCarts(User user);

    public Cart getLatestCartForUser(User user) throws SQLException;

    public int createCartForUser(int id);
}
