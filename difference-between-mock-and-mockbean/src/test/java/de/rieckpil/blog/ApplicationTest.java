package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @Autowired private StockApiClient stockApiClient;

  @MockBean private ExpensiveRealtimeStockApiClient expensiveRealtimeStockApiClient;

  @Test
  void contextLoadsWithAllBeans() {
    // e.g. now use the WebTestClient to access our endpoint
  }
}
