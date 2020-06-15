package de.rieckpil.blog;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

  public List<Book> getBooks() {
    Faker faker = new Faker();
    List<Book> books = new ArrayList();

    for (int i = 0; i < 10; i++) {
      Book book = new Book();
      book.setGenre(faker.book().genre());
      book.setTitle(faker.book().title());
      book.setIsbn(UUID.randomUUID().toString());
      books.add(book);
    }

    return books;
  }
}
