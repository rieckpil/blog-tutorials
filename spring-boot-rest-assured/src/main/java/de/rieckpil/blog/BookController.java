package de.rieckpil.blog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks(@RequestParam(value = "amount", defaultValue = "500") int amount) {
    return bookService.getAllBooks(amount);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bookService.getBookById(id));
  }

  @PostMapping
  public ResponseEntity<Void> createNewBook(
    @Valid @RequestBody BookRequest bookRequest,
    UriComponentsBuilder uriComponentsBuilder) {

    Long bookId = bookService.createNewBook(bookRequest);

    UriComponents uriComponents = uriComponentsBuilder.path("/api/books/{id}").buildAndExpand(bookId);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriComponents.toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}
