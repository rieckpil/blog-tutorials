package de.rieckpil.blog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import com.launchdarkly.sdk.server.integrations.FileData;

public class FeatureFlagClientFactory {

  public static FeatureFlagClient buildOnlineClient(String accessKey) {
    LDClient onlineClient = new LDClient(accessKey);
    return new LaunchDarklyFeatureFlagClient(onlineClient);
  }

public static FeatureFlagClient buildOfflineFileBasedClient(String inMemoryFileLocation) {
  Path localFeatureFlagStateFilePath = Paths.get(inMemoryFileLocation);

  if (Files.exists(localFeatureFlagStateFilePath)) {
    LDClient fileBaseClient = new LDClient(
      "invalid-ignored-access-key",
      new LDConfig.Builder()
        .dataSource(
          FileData.dataSource().filePaths(localFeatureFlagStateFilePath).autoUpdate(true))
        .events(Components.noEvents())
        .build());

    return new LaunchDarklyFeatureFlagClient(fileBaseClient);
  } else {
    return buildOfflineClient();
  }
}

public static FeatureFlagClient buildOfflineClient() {
  LDClient offlineClient = new LDClient("ignored-access-key", new LDConfig.Builder().offline(true).build());
  return new LaunchDarklyFeatureFlagClient(offlineClient);
}
}
