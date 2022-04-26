package de.rieckpil.blog;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import com.launchdarkly.sdk.server.integrations.TestData;

public class TestDataFeatureFlagClient implements FeatureFlagClient {

  private final TestData testData;
  private final LDClient ldClient;

  public TestDataFeatureFlagClient() {
    this.testData = TestData.dataSource();
    this.ldClient = new LDClient(
      "ignored-access-key",
      new LDConfig.Builder().dataSource(testData).events(Components.noEvents()).build());
  }

  @Override
  public String getCurrentValue(String featureFlagKey, String username) {
    return ldClient.stringVariation(featureFlagKey, new LDUser.Builder(username).build(), "unknown");
  }

  @Override
  public void registerChangeListener(String featureFlagKey, String username, FeatureFlagValueChangeHandler changeHandler) {
    ldClient
      .getFlagTracker()
      .addFlagValueChangeListener(featureFlagKey, new LDUser.Builder(username).build(), (changeEvent) -> changeHandler.handle(changeEvent.getOldValue().stringValue(), changeEvent.getNewValue().stringValue()));
  }

  public void updateFeatureFlag(String featureFlag, String newValue) {
    this.testData.update(this.testData.flag(featureFlag).valueForAllUsers(LDValue.of(newValue)));
  }
}
