package dao;

import models.User;

import java.sql.SQLException;

public interface UsersDao {
    User getUserById(int id) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    long addUser(User user) throws SQLException;
    boolean updateUserPassword(int id, String newpassword);
}
