package services;

import models.User;

public interface EmailService {
   public boolean sendEmail(User user, String subject, String html);
}
