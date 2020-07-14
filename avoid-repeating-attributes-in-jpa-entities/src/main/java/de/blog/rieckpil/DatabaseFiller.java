package de.blog.rieckpil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFiller implements CommandLineRunner {

  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  public DatabaseFiller(CustomerRepository customerRepository, ProductRepository productRepository) {
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Customer c1 = new Customer();
    c1.setName("John Doe");
    c1.setCustomerId("XYZ1337");

    Product p1 = new Product();
    p1.setAmount(100);
    p1.setName("Keyboard");

    customerRepository.save(c1);
    productRepository.save(p1);
  }
}
