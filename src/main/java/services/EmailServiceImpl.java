package services;

import com.sanctionco.jmail.JMail;
import com.sanctionco.jmail.TopLevelDomain;
import models.User;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import Config.Config;
import java.io.File;


public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(User user, String message, String subject, String html) {
        File propertiesFile = new File("../../../../simplemail.properties");
        ConfigLoader.loadProperties(propertiesFile,false); // optional default
        Email email = EmailBuilder.startingBlank()
                .to("Dear Client", user.getEmail())
                .withReplyTo("Manga Adlister Codeup Group", "mangaadlistercodeup@gmail.com")
                .withSubject(subject)
                .withHTMLText(html)
                .withHeader("X-Priority", 5)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(Config.smtpHost, Config.smtpPort, Config.smtpUsername, Config.smtpPassword)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailValidator() // turns off email validation
                .withEmailValidator( // or not
                        JMail.strictValidator()
                                .requireOnlyTopLevelDomains(TopLevelDomain.DOT_COM)
                                .withRule(em -> em.localPart().startsWith("allowed")))
                .withDebugLogging(true)
                .async()
                .buildMailer();

        mailer.sendMail(email);
    }
}
