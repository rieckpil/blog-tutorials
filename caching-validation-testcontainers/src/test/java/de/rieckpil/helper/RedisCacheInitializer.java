package de.rieckpil.helper;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class RedisCacheInitializer implements BeforeAllCallback {

  private final int REDIS_PORT = 6379;
  private final String REDIS_PASSWORD = RandomString.make(10);
  private final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis:7.2.4");

  private final GenericContainer<?> redisContainer =
      new GenericContainer<>(REDIS_IMAGE)
          .withExposedPorts(REDIS_PORT)
          .withCommand("redis-server", "--requirepass", REDIS_PASSWORD);

  @Override
  public void beforeAll(final ExtensionContext context) {
    log.info("Creating cache container : {}", REDIS_IMAGE);
    redisContainer.start();
    addCacheProperties();
    log.info("Successfully started cache container : {}", REDIS_IMAGE);
  }

  private void addCacheProperties() {
    System.setProperty("spring.data.redis.host", redisContainer.getHost());
    System.setProperty(
        "spring.data.redis.port", String.valueOf(redisContainer.getMappedPort(REDIS_PORT)));
    System.setProperty("spring.data.redis.password", REDIS_PASSWORD);
  }
}
