package services;

import models.User;

public interface EmailService {
   public void sendEmail(User user, String message, String subject, String html);
}
