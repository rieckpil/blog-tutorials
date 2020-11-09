package de.rieckpil.blog;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StockApiClient {

  public BigDecimal getLatestStockPrice(String stockCode) {
    // Fetch stock price via HTTP
    return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble());
  }
}
