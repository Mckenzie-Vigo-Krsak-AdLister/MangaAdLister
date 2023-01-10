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
    public boolean sendEmail(User user, String subject, String html) {
        try {
            Email email = EmailBuilder.startingBlank()
                    .from("Manga Adlister Codeup", "no-reply@mangaadlistercodeup.com")
                    .to("Dear Mangalister", user.getEmail())
                    .withSubject(subject)
                    .withHTMLText(html)
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer(Config.smtpHost, Config.smtpPort, Config.smtpUsername, Config.smtpPassword)
                    //                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .withSessionTimeout(10 * 1000)
                    .withEmailValidator( // or not
                            JMail.strictValidator()
                                    .requireOnlyTopLevelDomains(TopLevelDomain.DOT_COM)
                    )
                    .withDebugLogging(true)
                    .async()
                    .buildMailer();

            mailer.sendMail(email);

            return true;
        }catch(Exception e){
            System.out.println("Error while sending email.");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
