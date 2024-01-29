package de.rieckpil.blog;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @GetMapping
  public BigDecimal getStockPrice(@RequestParam("stockCode") String stockCode) {
    return stockService.getLatestPrice(stockCode);
  }
}
