package dao;

import models.CartItem;

import java.util.List;

public interface CartItemDao {
    public CartItem[] getCartItemsForCart(int cartid);
    public List<CartItem> getCartItemsForUser(int userid);

    public boolean addCartItem(int user,int cartid,int listingId);

    boolean removeItemById(int id);
}
