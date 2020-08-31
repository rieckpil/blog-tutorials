package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private RandomQuoteClient randomQuoteClient;

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Autowired
  private BookRepository bookRepository;

  @Test
  void contextLoads() {
    assertNotNull(testRestTemplate);
    assertNotNull(randomQuoteClient);
    assertNotNull(shoppingCartRepository);
    assertNotNull(bookRepository);
  }

}
