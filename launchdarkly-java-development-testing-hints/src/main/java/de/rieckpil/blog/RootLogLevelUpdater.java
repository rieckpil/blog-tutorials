package de.rieckpil.blog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RootLogLevelUpdater {

  private static final Logger LOG = LoggerFactory.getLogger(RootLogLevelUpdater.class);

  private final FeatureFlagClient featureFlagClient;

  public RootLogLevelUpdater(FeatureFlagClient featureFlagClient) {
    this.featureFlagClient = featureFlagClient;
  }

  public void registerListener() {

    LOG.info("Going to register root log level updater");

    featureFlagClient.registerChangeListener(
      "root-log-level",
      "duke",
      (oldValue, newValue) -> {

        LOG.info("Going to change the root log level from '{}' to '{}'", oldValue, newValue);

        Configurator.setRootLevel(Level.valueOf(newValue));
      });
  }
}
