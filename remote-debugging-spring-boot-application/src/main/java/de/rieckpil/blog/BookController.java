package de.rieckpil.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping
  public List<Book> getAllBooks() {
    log.info("Retrieving all available books");
    List<Book> allAvailableBooks = bookRepository.findAll();
    return allAvailableBooks;
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable("id") Long id) {
    log.info("Retrieving book with id: {}", id);
    Optional<Book> book = bookRepository.findById(id);

    if (book.isEmpty()) {
      throw new BookNotFoundException("Can't find book with id: " + id);
    }

    return book.get();
  }
}
