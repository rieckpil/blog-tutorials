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

    Faker faker = new Faker();

    log.info("Starting book initialization ...");

    for (int i = 0; i < 10; i++) {

      Book book = new Book();
      book.setTitle(faker.book().title());
      book.setAuthor(faker.book().author());
      book.setIsbn(UUID.randomUUID().toString());

      bookRepository.save(book);
    }

    log.info("... finished book initialization");

  }
}
