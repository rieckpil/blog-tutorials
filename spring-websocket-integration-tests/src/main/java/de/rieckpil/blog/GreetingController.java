package de.rieckpil.blog;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

  @MessageMapping("/welcome")
  @SendTo("/topic/greetings")
  public String greeting(String payload) {
    System.out.println("Generating new greeting message for " + payload);
    return "Hello, " + payload + "!";
  }

  @SubscribeMapping("/chat")
  public Message sendWelcomeMessageOnSubscription() {
    Message welcomeMessage = new Message();
    welcomeMessage.setMessage("Hello World!");
    return welcomeMessage;
  }

}
