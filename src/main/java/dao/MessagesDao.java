package dao;

import models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessagesDao {

    public boolean createMessage(Message newMessage);
    public Message[] allMessages();
    public boolean updateMessage(long id, Message updateMessage);
    public boolean deleteMessage(long id);
    public Message getMessageById(long id) throws SQLException;
    public Message getFromMessageById(long fromId) throws SQLException;
    public Message getToMessageById(long toId) throws SQLException;

}
