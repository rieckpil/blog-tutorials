package de.rieckpil.blog;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class NotificationService {

  private final JavaMailSender javaMailSender;

  public NotificationService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void notifyUser(String email, String content) {
    try {
      MimeMessage mail = javaMailSender.createMimeMessage();

      mail.addHeader("Content-type", "text/plain; charset=UTF-8");
      mail.setFrom(new InternetAddress("admin@spring.io"));
      mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      mail.setSubject("A new message for you");
      mail.setText(content);

      this.javaMailSender.send(mail);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
