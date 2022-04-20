package de.rieckpil.blog;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryFeatureFlagStoreTest {

  private static final String SIMPLE_FILE_NAME = "simple-in-memory-flags.json";
  private static final String COMPLEX_FILE_NAME = "complex-in-memory-flags.json";

  private static final Logger LOG = LoggerFactory.getLogger(InMemoryFeatureFlagStoreTest.class);

  private final File featureFlagStateFile;

  public InMemoryFeatureFlagStoreTest(File parent) {
    this.featureFlagStateFile = new File(parent, SIMPLE_FILE_NAME);
  }

  public void initializeEmptyState() {
    try {
      JSONObject featureFlagState = new JSONObject().put("flagValues", new JSONObject());
      Files.write(
        featureFlagStateFile.toPath(),
        featureFlagState.toString().getBytes(StandardCharsets.UTF_8));
    } catch (Exception exception) {
      LOG.error("Failed to initialize empty in-memory feature flag state", exception);
    }
  }

  public void updateFeature(String featureFlagKey, Object featureFlagValue) {
    try {
      JSONObject featureFlagState =
        new JSONObject()
          .put("flagValues", new JSONObject().put(featureFlagKey, featureFlagValue));

      Files.write(
        featureFlagStateFile.toPath(),
        featureFlagState.toString().getBytes(StandardCharsets.UTF_8));
    } catch (Exception exception) {
      LOG.error("Failed to update in-memory feature flag state", exception);
    }
  }

  public Path getPath() {
    return featureFlagStateFile.toPath();
  }
}

