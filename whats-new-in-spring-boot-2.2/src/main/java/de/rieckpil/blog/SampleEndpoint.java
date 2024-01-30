package de.rieckpil.blog;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleEndpoint {

  private SampleService sampleService;

  private SampleEndpoint(SampleService sampleService) {
    this.sampleService = sampleService;
  }

  @PostConstruct
  public void init() {
    System.out.println("SampleEndpoint is now initialized");
  }

  @GetMapping("/hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok(sampleService.getMessage());
  }
}
