package de.rieckpil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private static final List<Customer> CUSTOMER_LIST = new ArrayList<>(
    List.of(new Customer("Duke", "Java", 42L))
  );

  @GetMapping
  public List<Customer> getAllCustomers() {
    return CUSTOMER_LIST;
  }

  @PostMapping
  public ResponseEntity<Void> createCustomer(
    @RequestBody Customer customer,
    UriComponentsBuilder uriComponentsBuilder) {

    CUSTOMER_LIST.add(customer);

    return ResponseEntity
      .created(uriComponentsBuilder.path("/api/customers/{id}")
        .buildAndExpand(customer.id()).toUri())
      .build();
  }
}
