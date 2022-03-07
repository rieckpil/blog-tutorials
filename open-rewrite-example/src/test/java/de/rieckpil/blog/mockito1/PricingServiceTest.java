package de.rieckpil.blog.mockito1;

import java.math.BigDecimal;

import de.rieckpil.blog.PricingService;
import de.rieckpil.blog.ProductReporter;
import de.rieckpil.blog.ProductVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Test to demonstrate the automatic migration from Mockito 1 to Mockito 3 using OpenRewrite.
 */
// changed to org.mockito.junit.MockitoJUnitRunner with Mockito 3
@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {

  private ProductVerifier mockedProductVerifier = mock(ProductVerifier.class);
  private ProductReporter mockedProductReporter = mock(ProductReporter.class);

  @Test
  public void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    // changed to ArgumentMatchers with Mockito 3
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor(Matchers.startsWith("Air"))).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("AirPods"));

    // changed to ArgumentMatchers with Mockito 3
    verify(mockedProductReporter).notify(Matchers.anyString());
  }

  @Test
  public void shouldReturnExpensivePriceWhenProductIsNotInStockOfCompetitor() {
    // changed to ArgumentMatchers with Mockito 3
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor(Matchers.startsWith("MacBook"))).thenReturn(false);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    // changed to ArgumentMatchers with Mockito 3
    assertEquals(new BigDecimal("149.99"), classUnderTest.calculatePrice("MacBook"));

    // changed to verifyNoInteractions with Mockito 3
    verifyZeroInteractions(mockedProductReporter);
  }
}
