package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
class ShoppingCartRepositoryTest {

  @Autowired private MongoTemplate mongoTemplate;

  @Autowired private ShoppingCartRepository shoppingCartRepository;

  @Test
  public void shouldCreateContext() {
    shoppingCartRepository.save(
        new ShoppingCart("42", List.of(new ShoppingCartItem(new Item("MacBook", 999.9), 2))));

    assertNotNull(mongoTemplate);
    assertNotNull(shoppingCartRepository);
  }
}
