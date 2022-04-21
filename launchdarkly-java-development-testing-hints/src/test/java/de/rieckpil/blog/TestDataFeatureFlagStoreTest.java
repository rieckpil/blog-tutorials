package de.rieckpil.blog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import com.launchdarkly.sdk.server.integrations.TestData;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDataFeatureFlagStoreTest {

  @Test
  void shouldUseTestDataFeatureFlagStore() {

    CountDownLatch countDownLatch = new CountDownLatch(1);

    TestData testData = TestData.dataSource();

    testData.update(
      testData
        .flag("root-log-level")
        .valueForAllUsers(LDValue.of("ERROR")));

    LaunchDarklyFeatureFlagClient cut =
      new LaunchDarklyFeatureFlagClient(
        new LDClient(
          "ignored-access-key",
          new LDConfig.Builder().dataSource(testData).events(Components.noEvents()).build()));

    cut.registerChangeListener(
      "root-log-level",
      "duke",
      (oldValue, newValue) -> {
        countDownLatch.countDown();
      });

    testData.update(
      testData
        .flag("root-log-level")
        .valueForAllUsers(LDValue.of("DEBUG")));

    await()
      .atMost(5, TimeUnit.SECONDS)
      .untilAsserted(() -> assertEquals(0, countDownLatch.getCount()));
  }
}
