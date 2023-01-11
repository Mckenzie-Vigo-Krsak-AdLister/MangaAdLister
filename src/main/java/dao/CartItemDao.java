package dao;

import models.CartItem;

public interface CartItemDao {
    public CartItem[] getCartItemsForCart(int cartid);
    public CartItem[] getCartItemsForUser(int userid);
}
