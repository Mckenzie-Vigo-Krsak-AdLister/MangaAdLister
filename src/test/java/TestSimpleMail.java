import dao.DaoFactory;
import dao.UsersDao;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.EmailService;
import services.EmailServiceImpl;

import java.sql.SQLException;

public class TestSimpleMail {
    private EmailService emailService;
    private UsersDao userDao;
    @Before
    public void init() throws SQLException {
        try {
            emailService = new EmailServiceImpl();
            userDao = DaoFactory.getUsersDao();
        }catch(Exception e){
            System.out.println("Error initializing user and email service.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSendEmailService() throws SQLException {
        System.out.println("Sending email to aldanisvigo@gmail.com");
        boolean sent = emailService.sendEmail(this.userDao.getUserByEmail("aldanisvigo@gmail.com"),"Test Message","Hello");
        Assert.assertEquals(true,sent);
    }
}
