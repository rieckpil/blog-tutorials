package de.rieckpil.blog.sendemailswithsendgridandspringboot;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MailController {

  @Autowired
  private SendGrid sendGrid;

  @Value("${templateId}")
  private String EMAIL_TEMPLATE_ID;

  @GetMapping("/sendgrid")
  public String sendEmailWithSendGrid(@RequestParam("msg") String message) {

    Email from = new Email("yourname@yourhostname.de");
    String subject = "Hello World!";
    Email to = new Email("yourname@yourhostname.de");
    Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>" + message);

    Mail mail = new Mail(from, subject, to, content);

    mail.setReplyTo(new Email("yourname@yourhostname.de"));
    mail.personalization.get(0).addSubstitution("-username-", "Some blog user");
    mail.setTemplateId(EMAIL_TEMPLATE_ID);

    Request request = new Request();
    Response response;

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      response = sendGrid.api(request);

      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    return "email was successfully send";
  }
}
