package de.rieckpil.blog;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class StockApiClient {

  public BigDecimal getLatestStockPrice(String stockCode) {
    // Fetch stock price via HTTP
    return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble());
  }
}
