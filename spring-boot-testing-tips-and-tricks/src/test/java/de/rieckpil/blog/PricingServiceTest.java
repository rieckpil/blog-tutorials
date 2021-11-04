package de.rieckpil.blog;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// register the Mockito JUnit Jupiter extension
@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

  @Mock // Instruct Mockito to mock this object
  private ProductVerifier mockedProductVerifier;

  @Mock
  private ProductReporter mockedProductReporter;

  @Test
  void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    //Specify what boolean value to return for this test
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("AirPods"));

    //verify the interaction
    verify(mockedProductReporter).notify("AirPods");
  }

  @Test
  void hamcrestExample() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    assertThat(classUnderTest.calculatePrice("AirPods"), equalTo(new BigDecimal("99.99")));

    verify(mockedProductReporter).notify("AirPods");
  }

  @Test
  void assertjExample() {
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    org.assertj.core.api.Assertions.assertThat(classUnderTest.calculatePrice("AirPods")).isEqualTo(new BigDecimal("99.99"));

    verify(mockedProductReporter).notify("AirPods");
  }

}
