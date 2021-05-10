package de.rieckpil.blog;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  public Long register(String customerId) {
    System.out.println("Registering customer with id: " + customerId);
    return 42L;
  }
}
