package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
class StockControllerTest {

  @MockBean
  private StockService stockService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnStockPriceFromService() throws Exception {
    when(stockService.getLatestPrice("AMZN"))
      .thenReturn(BigDecimal.TEN);

    this.mockMvc
      .perform(get("/api/stocks?stockCode=AMZN"))
      .andExpect(status().isOk());
  }
}
