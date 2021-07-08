package de.rieckpil.blog.exercise8;

import java.time.ZonedDateTime;
import java.util.List;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AdvancedRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void shouldReturnCustomerThatJoinedTheEarliest() {

    customerRepository.deleteAll();

    Customer customerOne = new Customer("duke", ZonedDateTime.now());
    Customer customerTwo = new Customer("anna", ZonedDateTime.now().minusMinutes(42));
    Customer customerThree = new Customer("mike", ZonedDateTime.now().minusDays(42));

    customerRepository.saveAll(List.of(customerOne, customerTwo, customerThree));

    Customer result = customerRepository.getEarlyBird();

    assertEquals("mike", result.getName());
  }
}
