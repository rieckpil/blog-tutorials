package de.rieckpil.blog.customer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Customer> returnAllCustomers() {
    return createSampleCustomers();
  }

  private List<Customer> createSampleCustomers() {

    Customer customerOne = new Customer();
    customerOne.setId(UUID.randomUUID().toString());
    customerOne.setTags(Set.of("VIP", "PLATINUM_MEMBER", "EARLY_BIRD"));
    customerOne.setUsername("duke42");
    customerOne.setAddress(new Address("Berlin", "Germany", "12347"));
    customerOne.setOrders(List.of(
      new Order(List.of(
        new Product("MacBook Pro", BigDecimal.valueOf(1499.99), 3L),
        new Product("Kindle Paperwhite", BigDecimal.valueOf(149.00), 10L)
      ), "DEBIT"),
      new Order(List.of(
        new Product("Milk", BigDecimal.valueOf(0.99), 12L),
        new Product("Chocolate", BigDecimal.valueOf(2.99), 42L)
      ), "CREDIT_CARD")));

    Customer customerTwo = new Customer();
    customerTwo.setId(UUID.randomUUID().toString());
    customerTwo.setTags(Set.of("BRONZE_MEMBER", "DELAYED_PAYMENTS"));
    customerTwo.setUsername("alice");
    customerTwo.setAddress(new Address("Paris", "France", "75000"));
    customerTwo.setOrders(List.of(
      new Order(List.of(
        new Product("iPhone 12", BigDecimal.valueOf(999.99), 12L),
        new Product("Laptop", BigDecimal.valueOf(649.00), 10L)
      ), "PAYPAL")));

    Customer customerThree = new Customer();
    customerThree.setId(UUID.randomUUID().toString());
    customerThree.setTags(Set.of("GOLD_MEMBER"));
    customerThree.setUsername("bob");
    customerThree.setAddress(new Address("São Paulo", "Brazil", "17800-000"));
    customerThree.setOrders(List.of(
      new Order(List.of(
        new Product("MacBook Pro", BigDecimal.valueOf(2499.99), 2L),
        new Product("Kindle", BigDecimal.valueOf(99.00), 5L)
      ), "DEBIT"),
      new Order(List.of(
        new Product("Chewing Gum", BigDecimal.valueOf(0.49), 100L)
      ), "DEBIT")));

    return Arrays.asList(customerOne, customerTwo, customerThree);
  }

  @PostMapping
  public ResponseEntity<Void> createNewUser(
    @RequestBody CustomerCreationRequest request,
    UriComponentsBuilder uriComponentsBuilder) {

    System.out.println(request);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/api/customers/{id}")
        .buildAndExpand("42")
        .toUri())
      .build();
  }
}
