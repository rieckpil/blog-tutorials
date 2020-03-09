package de.rieckpil.blog.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

  @Mock
  private ProductVerifier mockedProductVerifier;

  @Mock
  private ProductReporter mockedProductReporter;

  @Test
  public void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService cut = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
  }
}
