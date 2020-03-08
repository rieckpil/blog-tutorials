package de.rieckpil.blog.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PricingServiceTest {

  @Mock
  private ProductVerifier mockedProductVerifier;

  @Mock
  private ProductReporter mockedProductReporter;

  @Test
  public void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService cut = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
    verify(mockedProductReporter).notify("AirPods");
  }
}
