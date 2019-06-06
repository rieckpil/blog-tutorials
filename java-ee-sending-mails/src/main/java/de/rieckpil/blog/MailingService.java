package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailingService {

    @Inject
    @ConfigProperty(name = "email")
    private String emailAddress;


    @Resource(name = "mail/localsmtp")
    private Session mailSession;

    public void sendSimpleMail() {

        Message simpleMail = new MimeMessage(mailSession);

        try {
            simpleMail.setSubject("Hello World from Java EE!");
            simpleMail.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            simpleMail.setContent("<p>Hello <b>World!</b></p>", "text/html; charset=utf-8");
            Transport.send(simpleMail);
            System.out.println("Message successfully send to: " + emailAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
