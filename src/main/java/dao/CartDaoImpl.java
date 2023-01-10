package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.Cart;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao{
    Connection connection = null;

    public CartDaoImpl(){
        try {

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);

        } catch (Exception e) {
            throw new RuntimeException("DataBase Error", e);
        }
    }

    @Override
    public Cart getCartById(int id) throws SQLException {
        try {
            //Create a prepared statement that pulls a cart by it's id
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart WHERE id = ?;");
            stmt.setInt(1, id);
            stmt.execute(); //Execute it

            //Advance to the first row in the result set
            ResultSet rs = stmt.getResultSet();
            rs.next();

            //Grab the cart information and generate a new cart object and return it
            int cartId = rs.getInt(0);
            int cartUserId = rs.getInt(1);
            Timestamp created = rs.getTimestamp(2);

            //Generate and return the cart
            Cart theCart = new Cart(cartId, cartUserId,created);
            return theCart;
        }catch(Exception e){
            System.out.println("Error while getting cart by id.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Cart[] getUserCarts(User user) {
        try{
            //Create a prepared statement that pulls all carts belonging to a user id
            int id = user.getId();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart WHERE users_id = ?;");
            stmt.setInt(1,id);
            stmt.execute();

            List<Cart> cartList = new ArrayList<>();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                //Grab the cart information and generate a new cart object and return it
                int cartId = rs.getInt(0);
                int cartUserId = rs.getInt(1);
                Timestamp created = rs.getTimestamp(2);
                //Generate and return the cart
                Cart theCart = new Cart(cartId, cartUserId, created);
                cartList.add(theCart);
            }
            return (Cart[]) cartList.toArray();
        }catch(Exception e){
            System.out.println("Error while getting carts for user.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Cart getLatestCartForUser(User user) throws SQLException {
        try{
            int id = user.getId();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart WHERE users_id = ? ORDER BY created LIMIT 1;");
            stmt.setInt(1,id);

            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            rs.next();

            //Generate the Cart
            Timestamp created = rs.getTimestamp("created");
            int userid = rs.getInt("users_id");
            int cartid = rs.getInt("id");
            Cart theCart = new Cart(cartid,userid,created);

            return theCart;
        }catch(Exception e){
            System.out.println("Error while getting the latest cart for the given user on CartDaoImpl");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
