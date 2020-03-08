package de.rieckpil.blog.pricing;

import org.springframework.stereotype.Service;

@Service
public class ProductReporter {

  public void notify(String productName) {
    System.out.println(productName + " is also in stock of the competitor");
  }
}
