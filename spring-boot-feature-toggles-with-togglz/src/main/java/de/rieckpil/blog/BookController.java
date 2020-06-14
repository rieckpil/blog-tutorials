package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.time.LocalDate;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private FeatureManager featureManager;

  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping
  public ResponseEntity<JsonNode> getBook() {

    ObjectNode book = objectMapper.createObjectNode();
    book.put("title", "Spring Boot 2.1");
    book.put("author", "Duke");
    book.put("pages", 42);

    if (featureManager.isActive(BookstoreFeatures.EXTENDED_INFORMATION)) {
      book.put("description", "Book about using Spring Boot");
      book.put("publishedAt", LocalDate.now().toString());
    }

    if (featureManager.isActive(BookstoreFeatures.PUBLIC_PRICES)) {
      book.put("price", 89.50);
    }

    return ResponseEntity.ok(book);
  }

  @GetMapping
  @RequestMapping("/wishlist")
  public ResponseEntity<ArrayNode> getBookWishlist() {

    if (featureManager.isActive(new NamedFeature("BOOK_WISHLIST"))) {
      ArrayNode array = objectMapper.createArrayNode();
      array.add("Spring Boot 2.2");
      array.add("Spring Framework 6.0");
      return ResponseEntity.ok(array);
    }

    return ResponseEntity.noContent().build();
  }
}
