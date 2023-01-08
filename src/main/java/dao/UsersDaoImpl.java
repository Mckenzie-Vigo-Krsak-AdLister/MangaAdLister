package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.User;

import java.sql.*;

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
            PreparedStatement stmt = (PreparedStatement) connection.createStatement();
            stmt.execute("SELECT * FROM users WHERE id = ?;"); // Select the user matching the id
            stmt.setInt(1, id); // Set the id field in the prepared statement
            ResultSet results = stmt.getResultSet(); // Retrieve the result set
            results.next(); // Initialize the iterator
            return User.userFromResultSet(results); //Return the user from the result set
        } catch (SQLException e){
            throw e;
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        try {
            PreparedStatement stmt = (PreparedStatement) connection.createStatement();
            stmt.execute("SELECT * FROM users WHERE email = ?;"); // Select all users matching the email
            stmt.setString(1,email); // Set the email field in the prepared statement
            ResultSet results = stmt.getResultSet(); // Retrieve the result set
            results.next(); // Initialize the iterator
            //Return the user from the result set
            return User.userFromResultSet(results);
        } catch ( SQLException  e ){
            throw e;
        }
    }
}
