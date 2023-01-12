package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties
public class CartItem implements Serializable {
    private int id;
    private int users_id;
    private int listing_id;
    private int cart_id;

    public CartItem(int id, int user_id, int listing_id, int cart_id){
        this.id = id;
        this.users_id = user_id;
        this.listing_id = listing_id;
        this.cart_id = cart_id;
    }
    public CartItem(int users_id, int listing_id, int cart_id){
        this.users_id = users_id;
        this.listing_id = listing_id;
        this.cart_id = cart_id;
    }

    public CartItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUsersId() {
        return this.users_id;
    }

    public int getListingId() {
        return this.listing_id;
    }

    public int getCartId(){
        return this.cart_id;
    }

    public void setUsersId(int users_id){
        this.users_id = users_id;
    }

    public void setListingId(int listing_id){
        this.listing_id = listing_id;
    }

    public void setCartId(int cart_id){
        this.cart_id = cart_id;
    }
}
