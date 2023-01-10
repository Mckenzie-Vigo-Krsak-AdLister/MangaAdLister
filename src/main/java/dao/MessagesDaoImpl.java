package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.Message;

import java.sql.*;
import java.util.List;

public class MessagesDaoImpl implements MessagesDao {

    private Connection connection = null;

    private MessagesDaoImpl() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);
        } catch (Exception e) {
            throw new RuntimeException("DataBase Error", e);
        }
    }

    @Override
    public boolean createMessage(Message newMessage) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO manga_adlister.messages(message, time_stamp, from_id, to_id) VALUES(?, ?, ?, ?);");
            int numberRows = stmt.executeUpdate();
            return numberRows >= 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message[] allMessages() {
        return new Message[0];
    }

    @Override
    public boolean updateMessage (long id, Message updateMessage) {
        return false;
    }

    public boolean deleteMessage(long id) {
        return false;
    }

    public Message getMessageById(long id) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manga_adlister.messages WHERE id = ?;");
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            String message = rs.getString("message");
            Timestamp time_stamp = rs.getTimestamp("time_stamp");
            long fromId = rs.getLong("fromId");
            long toId = rs.getLong("toId");
            Message newMessage = new Message(message, time_stamp, fromId, toId);
            return newMessage;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message getFromMessageById(long fromId) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manga_adlister.messages WHERE from_id = ?;");
            stmt.setLong(1, fromId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            String message = rs.getString("message");
            Timestamp time_stamp = rs.getTimestamp("time_stamp");
            long toId = rs.getLong("to_id");
            Message newMessage = new Message(message, time_stamp, fromId, toId);
            return newMessage;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message getToMessageById(long toId) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM manga_adlister.messages WHERE to_id = ?;");
            stmt.setLong(1, toId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            String message = rs.getString("message");
            Timestamp time_stamp = rs.getTimestamp("time_stamp");
            long fromId = rs.getLong("from_id");
            Message newMessage = new Message(message, time_stamp, fromId, toId);
            return newMessage;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
