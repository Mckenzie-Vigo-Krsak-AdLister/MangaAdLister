package services;

import models.User;

public interface MessageService {

    boolean sendToFrom(User to, User from, String message);

}
