package de.rieckpil.blog;

@FunctionalInterface
public interface FeatureFlagValueChangeHandler {

  void handle(String oldValue, String newValue);
}
