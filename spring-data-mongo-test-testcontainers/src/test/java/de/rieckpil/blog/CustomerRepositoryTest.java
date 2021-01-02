package de.rieckpil.blog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CustomerRepositoryTest {

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Autowired
  private CustomerRepository customerRepository;

  @AfterEach
  void cleanUp() {
    this.customerRepository.deleteAll();
  }

  @Test
  void shouldReturnListOfCustomerWithMatchingRate() {
    this.customerRepository.save(new Customer("mike@spring.io", 1));
    this.customerRepository.save(new Customer("duke@spring.io", 42));
    this.customerRepository.save(new Customer("hannah@spring.io", 55));

    List<Customer> customers = customerRepository.findByRatingBetween(40, 56);

    assertEquals(2, customers.size());
  }

  @Test
  void shouldReturnOrderedListOfCustomer() {
    this.customerRepository.save(new Customer("mike@spring.io", 100));
    this.customerRepository.save(new Customer("duke@spring.io", 22));
    this.customerRepository.save(new Customer("hannah@spring.io", 99));

    List<Customer> customers = customerRepository.findByRatingBetween(1, 101);

    assertEquals("duke@spring.io", customers.get(0).getEmail());
    assertEquals("hannah@spring.io", customers.get(1).getEmail());
    assertEquals("mike@spring.io", customers.get(2).getEmail());
  }
}

