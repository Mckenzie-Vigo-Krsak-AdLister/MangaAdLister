package services;

import Config.Config;
import com.mysql.cj.jdbc.Driver;
import dao.MessagesDao;
import dao.MessagesDaoImpl;
import models.Message;
import models.User;

import java.sql.*;
import java.time.Instant;

public class MessageServiceImpl implements MessageService{

    private MessagesDao messagesDao = null;

    public MessageServiceImpl() {
        messagesDao = new MessagesDaoImpl();
    }


//    private Connection connection = null;
//
//    public MessageServiceImpl() {
//        try {
//
//            DriverManager.registerDriver(new Driver());
//            connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);
//
//        } catch (Exception e) {
//            throw new RuntimeException("DataBase Error", e);
//        }
//
//    }

    @Override
    public boolean sendToFrom(User to, User from, String message) {
        Timestamp timestamp = Timestamp.from(Instant.now());
        Message newMessage = new Message(message, timestamp, from.getId(), to.getId());
        return this.messagesDao.createMessage(newMessage);
    }
}
