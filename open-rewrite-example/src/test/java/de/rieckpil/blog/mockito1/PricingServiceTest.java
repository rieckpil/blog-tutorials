package de.rieckpil.blog.mockito1;

import java.math.BigDecimal;

import de.rieckpil.blog.PricingService;
import de.rieckpil.blog.ProductReporter;
import de.rieckpil.blog.ProductVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PricingServiceTest {

  private ProductVerifier mockedProductVerifier = mock(ProductVerifier.class);
  private ProductReporter mockedProductReporter = mock(ProductReporter.class);

  @Test
  void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("AirPods"));

    verify(mockedProductReporter).notify("AirPods");
  }
}
