package de.rieckpil.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @GetMapping
  public List<Customer> getAllCustomers() {
    return List.of(new Customer("Duke", "Java"));
  }

  @PostMapping
  public ResponseEntity<Void> createNewCustomer(@RequestBody Customer request, UriComponentsBuilder uriComponentsBuilder) {
    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/api/customers/{id}")
        .buildAndExpand("42")
        .toUri())
      .build();
  }
}
