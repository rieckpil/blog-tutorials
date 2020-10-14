package de.rieckpil.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  @GetMapping
  public List<Book> getAllBooks() {
    return List.of(new Book(1L, "42", "Effective Java"));
  }
}
