package de.rieckpil.blog.pricing;

import org.springframework.stereotype.Service;

@Service
public class ProductVerifier {

  public boolean isCurrentlyInStockOfCompetitor(String productName) {
    return false;
  }
}
