package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class SampleService {

  @PostConstruct
  public void init() {
    System.out.println("SampleService is now initialized");
  }

  public String getMessage() {
    return "Hello World";
  }
}
