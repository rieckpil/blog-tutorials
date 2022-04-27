package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private FeatureFlagClient featureFlagClient;

  @InjectMocks
  private OrderService orderService;

  @Test
  void shouldDeliverViaPlaneWhenConfigured() {
    when(featureFlagClient.getCurrentValue(anyString(), anyString()))
      .thenReturn("plane");

    orderService.processOrder("42");

    // further verification
  }
}
