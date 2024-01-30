package de.rieckpil.blog;

import java.io.InputStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BadPracticeFileReader implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    InputStream in = this.getClass().getResourceAsStream("/message.txt");
    byte[] allBytes = in.readAllBytes();
    System.out.println(new String(allBytes));
  }
}
