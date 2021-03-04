package de.rieckpil.blog.mailing;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

  private final Session session;

  public MailService(String host, Integer smtpPort, Integer imapPort, String username, String password) {
    this.session = createSession(host, smtpPort, imapPort, username, password);
  }

  private Session createSession(String host, Integer smtpPort, Integer imapPort, String username, String password) {
    Properties props = new Properties();

    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", smtpPort);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    props.put("mail.imap.host", host);
    props.put("mail.imap.port", imapPort);
    props.put("mail.imap.auth", "true");
    props.put("mail.imap.starttls.enable", "true");

    Authenticator authentication = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };

    return Session.getInstance(props, authentication);
  }

  public void sendToUser(String message, String recipient) throws MessagingException {
    Message mail = new MimeMessage(session);
    mail.addHeader("Content-type", "text/plain; charset=UTF-8");
    mail.setFrom(new InternetAddress("hello@duke.io"));
    mail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    mail.setSubject("A new message for you");
    mail.setText(message);

    Transport.send(mail);
  }

  public String retrieveLatestMailForUser(String recipient, String recipientPassword) throws Exception {
    try (Store store = session.getStore("imap")) {
      store.connect(recipient, recipientPassword);

      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_ONLY);

      Message[] messages = inbox.getMessages();
      String latestMessageContent = "NO NEW MESSAGE";

      if (messages.length > 0) {
        latestMessageContent = messages[0].getContent().toString();
      }

      return latestMessageContent;
    }
  }
}
