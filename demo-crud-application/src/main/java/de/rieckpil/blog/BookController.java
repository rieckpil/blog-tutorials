package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping
  public ResponseEntity<Void> createNewBook(@Valid @RequestBody BookRequest bookRequest, UriComponentsBuilder uriComponentsBuilder) {
    Long primaryKey = bookService.createNewBook(bookRequest);

    UriComponents uriComponents = uriComponentsBuilder.path("/api/books/{id}").buildAndExpand(primaryKey);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriComponents.toUri());

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bookService.getBookById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest) {
    return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
    bookService.deleteBookById(id);
    return ResponseEntity.ok().build();
  }

}
