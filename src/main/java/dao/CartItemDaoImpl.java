package dao;

import com.mysql.cj.jdbc.Driver;
import models.CartItem;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class CartItemDaoImpl implements CartItemDao{
    private Connection connection;

    public CartItemDaoImpl(){
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(Config.jdbcConnectionString,Config.mysqlUser,Config.mysqlPassword);
        }catch(Exception e){
            System.out.println("Error registering driver and getting connection to mysql in CartItemDao implementation.");
            System.out.println(e.getMessage());
        }
    }


    @Override
    public CartItem[] getCartItemsForCart(int cartid) {
        try{
            //Prepare a statement to pull a cart item by its id
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart_item WHERE cart_id = ?;");
            stmt.setInt(1,cartid);

            stmt.execute();

            List<CartItem> cartItems = new ArrayList<>();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                CartItem it = new CartItem(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                cartItems.add(it);
            }
            return (CartItem[]) cartItems.toArray();
        }catch(Exception e){
            System.out.println("Error while getting cart item by id.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CartItem> getCartItemsForUser(int userid) {
        try {
            //Prepare a statement to pull all the cart items for the given user
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart_item WHERE users_id = ?;");
            stmt.setInt(1,userid);

            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            List<CartItem> cartItems = new ArrayList<>();
            while(rs.next()){
                CartItem it = new CartItem(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                cartItems.add(it);
            }
            return cartItems;
        }catch(Exception e){
            System.out.println("Error while getting cart items for the give user.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addCartItem(int user, int cartid,int listingid) {
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO cart_item(users_id,listing_id,cart_id,created) VALUES(?,?,?,?);");
            stmt.setInt(1,user);
            stmt.setInt(2,listingid);
            stmt.setInt(3,cartid);
            stmt.setTimestamp(4, Timestamp.from(Instant.now()));
            return stmt.execute();
        }catch(Exception e){
            System.out.println("Error saving cart item to the database");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeItemById(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM cart_item WHERE id = ?;");
            stmt.setInt(1,id);

            return stmt.execute();
        }catch(Exception e){
            System.out.println("Error while deleting cart item from the database");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
