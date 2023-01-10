package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.Instant;

public class UsersDaoImpl implements UsersDao {
    private Connection connection = null;
    public UsersDaoImpl() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.jdbcConnectionString,
                    Config.mysqlUser,
                    Config.mysqlPassword
            );

        } catch (SQLException e) {
            throw new RuntimeException("DataBase Error", e);
        }

    }
    @Override
    public User getUserById(int id) throws SQLException {
        try {
            //Prepare the statement to pull the user
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
            stmt.setInt(1, id); // Set the id field in the prepared statement
            stmt.execute();
            ResultSet results = stmt.getResultSet(); // Retrieve the result set
            if(results.next()) {
                return User.userFromResultSet(results); //Return the user from the result set
            }else return null;
        } catch (SQLException e){
            throw e;
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
            stmt.setString(1,email); // Set the email field in the prepared statement
            stmt.execute();
            ResultSet results = stmt.getResultSet(); // Retrieve the result set
            if(results.next()) { // Initialize the iterator
                //Return the user from the result set
                return User.userFromResultSet(results);
            }else return null;
        } catch ( SQLException  e ){
            throw e;
        }
    }

    @Override
    public long addUser(User user) throws SQLException {
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(email,password,created,roles) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            String salt = BCrypt.gensalt();
            String hash = BCrypt.hashpw(user.getPassword(),salt);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, hash);
            stmt.setDate(3, new java.sql.Date(Date.from(Instant.now()).getTime()));
            stmt.setString(4, user.getRoles());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        }catch(Exception e){
            throw e;
        }
    }
}
