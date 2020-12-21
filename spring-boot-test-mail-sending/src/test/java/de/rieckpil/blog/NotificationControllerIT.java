package de.rieckpil.blog;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = {
    "spring.mail.password=springboot",
    "spring.mail.username=duke",
    "spring.mail.host=127.0.0.1",
    "spring.mail.port=3025",
    "spring.mail.protocol=smtp",
    "spring.mail.test-connection=true"
  })
class NotificationControllerIT {

  @RegisterExtension
  static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
    .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
    .withPerMethodLifecycle(false);

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldSendEmailWithCorrectPayloadToUser() throws Exception {

    String payload = "{ \"email\": \"duke@spring.io\", \"content\": \"Hello World!\"}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(payload, headers);

    ResponseEntity<Void> response = this.testRestTemplate.postForEntity("/notifications", request, Void.class);

    assertEquals(200, response.getStatusCodeValue());

    MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
    assertEquals("Hello World!", GreenMailUtil.getBody(receivedMessage));
    assertEquals(1, receivedMessage.getAllRecipients().length);
    assertEquals("duke@spring.io", receivedMessage.getAllRecipients()[0].toString());
  }

}
