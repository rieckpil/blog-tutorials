package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

  private final String welcomeMessage;

  public RootController(@Value("${welcome.message}") String welcomeMessage) {
    this.welcomeMessage = welcomeMessage;
  }

  @GetMapping
  public String getWelcomeMessage() {
    return welcomeMessage;
  }
}
