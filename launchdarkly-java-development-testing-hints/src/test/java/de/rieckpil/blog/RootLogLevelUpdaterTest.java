package de.rieckpil.blog;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RootLogLevelUpdaterTest {

  private TestDataFeatureFlagClient testDataFeatureFlagClient;
  private RootLogLevelUpdater cut;

  @BeforeEach
  void setUp() {
    this.testDataFeatureFlagClient = new TestDataFeatureFlagClient();
    this.cut = new RootLogLevelUpdater(testDataFeatureFlagClient);
  }

  @Test
  void shouldUpdateRootLogLevel() {
    assertNotEquals(Level.TRACE, LogManager.getRootLogger().getLevel());

    cut.registerListener();

    testDataFeatureFlagClient.updateFeatureFlag("root-log-level", "TRACE");

    await()
      .atMost(2, TimeUnit.SECONDS)
      .untilAsserted(() -> assertEquals(Level.TRACE, LogManager.getRootLogger().getLevel()));
  }
}
