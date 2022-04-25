package de.rieckpil.blog;

public class TestDataFeatureFlagClient implements FeatureFlagClient {

  @Override
  public String getCurrentValue(String featureFlagKey, String username) {
    return null;
  }

  @Override
  public void registerChangeListener(String featureFlagKey, String username, FeatureFlagValueChangeHandler changeHandler) {

  }

  public void updateFeatureFlag(String featureFlag, Object newValue) {

  }
}
