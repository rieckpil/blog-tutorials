package de.rieckpil.blog;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String message) {
    System.out.println(message);
    return "Hello, " + HtmlUtils.htmlEscape(message) + "!";
  }

  @MessageMapping("/object")
  @SendTo("/topic/objects")
  public Message greetingObject(Message message) {
    Message result = new Message();
    result.setMessage(message.getMessage().toUpperCase());
    return result;
  }

}
