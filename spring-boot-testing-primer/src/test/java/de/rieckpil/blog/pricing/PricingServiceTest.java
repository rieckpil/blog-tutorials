package de.rieckpil.blog.pricing;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

  @Mock
  private ProductVerifier mockedProductVerifier;

  @Test
  public void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
      .thenReturn(true);

    PricingService cut = new PricingService(mockedProductVerifier);

    assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
  }
}
