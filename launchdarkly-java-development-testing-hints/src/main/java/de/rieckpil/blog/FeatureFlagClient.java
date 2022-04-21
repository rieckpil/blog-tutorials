package de.rieckpil.blog;

public interface FeatureFlagClient {
  String getCurrentValue(String featureFlagKey, String username);

  void registerChangeListener(String featureFlagKey, String username, FeatureFlagValueChangeHandler changeHandler);
}
