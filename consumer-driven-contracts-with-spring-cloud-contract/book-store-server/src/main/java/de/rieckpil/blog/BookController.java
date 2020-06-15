package de.rieckpil.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getTodos() {
    return ResponseEntity.ok(bookService.getBooks());
  }
}
