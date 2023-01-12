package dao;

import models.CartItem;

import java.util.List;

public interface CartItemDao {
    public CartItem[] getCartItemsForCart(int cartid);
    public List<CartItem> getCartItemsForUser(int userid);

    public boolean addCartItem(CartItem item);
}
