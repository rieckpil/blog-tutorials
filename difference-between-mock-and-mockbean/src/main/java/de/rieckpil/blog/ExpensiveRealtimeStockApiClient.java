package de.rieckpil.blog;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

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
