package de.rieckpil.blog;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class RootLogLevelUpdaterTest {

  private TestDataFeatureFlagClient testDataFeatureFlagClient;
  private RootLogLevelUpdater cut;

  @BeforeEach
  void setUp() {
    this.testDataFeatureFlagClient = new TestDataFeatureFlagClient();
    this.cut = new RootLogLevelUpdater(testDataFeatureFlagClient);
  }

  @Test
  @Disabled
  void shouldUpdateRootLogLevel() {
    cut.registerListener();

    assertNotEquals(Level.TRACE, LogManager.getRootLogger().getLevel());

    await()
      .atMost(2, TimeUnit.SECONDS)
      .untilAsserted(() -> assertEquals(Level.TRACE, LogManager.getRootLogger().getLevel()));
  }
}
