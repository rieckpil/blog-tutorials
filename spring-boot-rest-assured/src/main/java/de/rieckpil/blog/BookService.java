package de.rieckpil.blog;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookService {

  private List<Book> bookStore = new ArrayList<>();

  public Long createNewBook(BookRequest bookRequest) {
    Book book = new Book();

    book.setIsbn(bookRequest.getIsbn());
    book.setAuthor(bookRequest.getAuthor());
    book.setTitle(bookRequest.getTitle());
    book.setId(Math.abs(ThreadLocalRandom.current().nextLong()));

    bookStore.add(book);

    return book.getId();
  }

  public List<Book> getAllBooks(int amount) {

    if (this.bookStore.size() > amount) {
      return this.bookStore.subList(0, amount - 1);
    }

    return this.bookStore;
  }

  public Book getBookById(Long id) {
    return this.bookStore
      .stream()
      .filter(book -> book.getId().equals(id))
      .findFirst()
      .orElseThrow(() -> new BookNotFoundException(String.format("Book with id: '%s' not found", id)));
  }
}
