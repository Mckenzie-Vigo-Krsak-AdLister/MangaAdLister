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
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(email,password,first_name,last_name,created,roles) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            String salt = BCrypt.gensalt();
            String hash = BCrypt.hashpw(user.getPassword(),salt);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, hash);
            stmt.setString(3,user.getFirstName());
            stmt.setString(4,user.getLastName());
            stmt.setDate(5, new java.sql.Date(Date.from(Instant.now()).getTime()));
            stmt.setString(6, user.getRoles());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public boolean updateUserPassword(int id, String newpassword) {
        try{
            //Hash the new password using BCrypt
            String hashedPass = BCrypt.hashpw(newpassword,BCrypt.gensalt());

            //Create a prepared statement to update the password for the user with the given id
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?;");
            stmt.setString(1,hashedPass);
            stmt.setInt(2,id);

            //If we have more at least one row updated
            if(stmt.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
