package de.rieckpil.blog;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.LDClient;

public class LaunchDarklyFeatureFlagClient implements FeatureFlagClient {

  public final LDClient ldClient;

  public LaunchDarklyFeatureFlagClient(LDClient ldClient) {
    this.ldClient = ldClient;
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
}
