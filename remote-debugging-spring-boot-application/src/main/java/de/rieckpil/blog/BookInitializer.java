package de.rieckpil.blog;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BookInitializer implements CommandLineRunner {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public void run(String... args) throws Exception {
    log.info("Initializing books ...");
    Faker faker = new Faker();

    for (int i = 0; i < 42; i++) {

      Book book = new Book();
      book.setAuthor(faker.book().author());
      book.setIsbn(UUID.randomUUID().toString());
      book.setTitle(faker.book().title());

      bookRepository.save(book);
    }
    log.info("... finished initialization");
  }
}
