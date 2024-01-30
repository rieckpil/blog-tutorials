package de.rieckpil.blog;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class ExpensiveRealtimeStockApiClient {

  @PostConstruct
  public void setup() {
    // setup expensive connection to Stock API
  }

  public BigDecimal getLatestStockPrice(String stockCode) {
    // Fetch stock price via HTTP
    return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble());
  }
}
