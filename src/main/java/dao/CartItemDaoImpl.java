package dao;

import com.mysql.cj.jdbc.Driver;
import models.CartItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public CartItem[] getCartItemsForUser(int userid) {
        try {
            //Prepare a statement to pull all the cart items for the given user
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart_item WHERE users_id = ?;");
            stmt.setInt(1,userid);

            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            List<CartItem> cartItems = new ArrayList<>();
            while(rs.next()){
                CartItem it = new CartItem(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                cartItems.add(it);
            }
            return (CartItem[]) cartItems.toArray();
        }catch(Exception e){
            System.out.println("Error while getting cart items for the give user.");
            System.out.println(e.getMessage());
            return null;
        }
    }
}