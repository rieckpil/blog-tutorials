package de.rieckpil.blog;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final JavaMailSender javaMailSender;

  public NotificationService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void notifyUser(String email, String content) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom("admin@spring.io");
    mail.setSubject("A new message for you");
    mail.setText(content);
    mail.setTo(email);

    this.javaMailSender.send(mail);
  }
}
