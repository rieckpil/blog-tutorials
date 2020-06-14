package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

  @Value("${message}")
  private String message;

  @GetMapping
  public ResponseEntity<String> getMessage() {
    return ResponseEntity.ok(message);
  }
}
