package de.rieckpil.blog.greenmail;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import de.rieckpil.blog.mailing.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailServiceTest {

  @RegisterExtension
  static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP_IMAP);

  private MailService cut;

  @BeforeEach
  public void setup() {

    greenMail.setUser("admin@java.io", "my_secret");

    this.cut = new MailService(
      greenMail.getSmtp().getBindTo(),
      greenMail.getSmtp().getPort(),
      greenMail.getImap().getPort(),
      "admin@java.io",
      "my_secret");
  }

  @Test
  void shouldSendMailToRecipient() throws Exception {
    cut.sendToUser("Hello from Test!", "mike@java.io");

    MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
    assertEquals("Hello from Test!", GreenMailUtil.getBody(receivedMessage));
    assertEquals(1, receivedMessage.getAllRecipients().length);
    assertEquals("mike@java.io", receivedMessage.getAllRecipients()[0].toString());
  }

  @Test
  void shouldReceiveLastMailForUser() throws Exception {

    Session smtpSession = greenMail.getSmtp().createSession();

    Message msg = new MimeMessage(smtpSession);
    msg.setFrom(new InternetAddress("test@java.io"));
    msg.addRecipient(Message.RecipientType.TO,
      new InternetAddress("mike@java.io"));
    msg.setSubject("Test");
    msg.setText("Hello World from GreenMail!");
    Transport.send(msg);

    greenMail.setUser("mike@java.io", "secret_mike");

    String result = cut.retrieveLatestMailForUser("mike@java.io", "secret_mike");

    assertEquals("Hello World from GreenMail!", result);
  }
}
