package de.rieckpil.blog.exercise4;

import java.math.BigDecimal;

import de.rieckpil.blog.pricing.PricingService;
import de.rieckpil.blog.pricing.ProductVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PricingServiceTest {

  @Mock
  private ProductVerifier mockedProductVerifier;

  @InjectMocks
  private PricingService cut;

  @Test
  void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    Mockito.when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
      .thenReturn(true);

    assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
  }

  @Test
  void shouldReturnHigherPriceWhenProductIsNotInStockOfCompetitor() {
    Mockito.when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
      .thenReturn(false);

    assertEquals(new BigDecimal("149.99"), cut.calculatePrice("AirPods"));
  }
}
