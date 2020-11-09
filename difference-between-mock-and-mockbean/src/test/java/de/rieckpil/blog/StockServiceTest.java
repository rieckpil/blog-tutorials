package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

  @Mock
  private StockApiClient stockApiClient;

  @InjectMocks
  private StockService cut;

  @Test
  void shouldReturnDefaultPriceWhenClientThrowsException() {

    when(stockApiClient.getLatestStockPrice("AMZN"))
      .thenThrow(new RuntimeException("Remote System Down!"));

    BigDecimal result = cut.getLatestPrice("AMZN");

    assertEquals(BigDecimal.ZERO, result);
  }
}
