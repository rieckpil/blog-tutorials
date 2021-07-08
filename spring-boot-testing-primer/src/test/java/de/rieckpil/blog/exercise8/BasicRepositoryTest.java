package de.rieckpil.blog.exercise8;


import java.time.ZonedDateTime;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BasicRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  void shouldSaveAndRetrieveJpaEntity() {
    Customer customerToStore = new Customer();
    customerToStore.setName("mike");
    customerToStore.setJoinedAt(ZonedDateTime.now());

    Customer result = testEntityManager.persistFlushFind(customerToStore);

    assertNotNull(result.getId());
  }
}
