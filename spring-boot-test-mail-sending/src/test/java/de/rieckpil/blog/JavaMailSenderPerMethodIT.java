package de.rieckpil.blog;

import javax.mail.internet.MimeMessage;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JavaMailSenderPerMethodIT {

  @RegisterExtension
  static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
    .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
    .withPerMethodLifecycle(true);

  @Autowired
  private JavaMailSender javaMailSender;

  @Test
  void verifyDeliveryToGreenMailServer() {
    sendEmailAndVerify();
  }

  @Test
  void verifyDeliveryToGreenMailServerSecond() {
    sendEmailAndVerify();
  }

  private void sendEmailAndVerify() {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom("admin@spring.io");
    mail.setSubject("A new message for you");
    mail.setText("Hello GreenMail!");
    mail.setTo("test@greenmail.io");

    javaMailSender.send(mail);

    // awaitility
    await().atMost(2, SECONDS).untilAsserted(() -> {
      MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
      assertEquals(1, receivedMessages.length);

      MimeMessage receivedMessage = receivedMessages[0];
      assertEquals("Hello GreenMail!", GreenMailUtil.getBody(receivedMessage));
      assertEquals(1, receivedMessage.getAllRecipients().length);
      assertEquals("test@greenmail.io", receivedMessage.getAllRecipients()[0].toString());
    });
  }
}
