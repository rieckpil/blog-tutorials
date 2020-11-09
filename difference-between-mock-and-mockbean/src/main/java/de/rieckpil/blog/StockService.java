package de.rieckpil.blog;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class StockService {

  private final StockApiClient stockApiClient;

  private Set<String> techCompanies = Set.of("AAPL", "MSFT", "GOOG");

  public StockService(StockApiClient stockApiClient) {
    this.stockApiClient = stockApiClient;
  }

  public BigDecimal getLatestPrice(String stockCode) {
    if (techCompanies.contains(stockCode)) {
      return BigDecimal.valueOf(Double.MAX_VALUE);
    }

    try {
      return stockApiClient.getLatestStockPrice(stockCode);
    } catch (Exception e) {
      e.printStackTrace();
      return BigDecimal.ZERO;
    }
  }
}
