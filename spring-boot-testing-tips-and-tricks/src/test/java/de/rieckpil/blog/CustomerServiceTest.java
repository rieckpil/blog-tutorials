package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// Don't do the following - the same can be tested with a much faster unit test
@SpringBootTest
class CustomerServiceTest {

  @Autowired private CustomerService cut;

  @MockBean private CustomerRepository customerRepository;

  @Test
  void shouldRegisterNewCustomer() {

    when(customerRepository.findByCustomerId("duke")).thenReturn(null);

    Long result = cut.register("duke");

    assertEquals(42L, result);
  }
}
