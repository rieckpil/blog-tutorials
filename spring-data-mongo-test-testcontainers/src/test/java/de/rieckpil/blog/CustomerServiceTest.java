package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataMongoTest
class CustomerServiceTest {

  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Autowired private MongoTemplate mongoTemplate;

  private CustomerService cut;

  @BeforeEach
  void setUp() {
    this.cut = new CustomerService(mongoTemplate);
  }

  @Test
  void shouldReturnCustomersWithRatingGreater90AsVIP() {
    this.mongoTemplate.save(new Customer("duke@spring.io", 42));
    this.mongoTemplate.save(new Customer("mike@spring.io", 91));
    this.mongoTemplate.save(new Customer("hannah@spring.io", 99));

    List<Customer> result = cut.findAllVIPCustomers();

    assertEquals(2, result.size());
  }
}
