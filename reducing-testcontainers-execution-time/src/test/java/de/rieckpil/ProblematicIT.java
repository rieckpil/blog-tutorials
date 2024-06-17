package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import de.rieckpil.utility.CacheUtility;
import java.time.Duration;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class ProblematicIT {

  @Autowired private CacheUtility cacheUtility;

  private static final int REDIS_PORT = 6379;
  private static final String REDIS_PASSWORD = RandomString.make(10);
  private static final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis:7.2.4");

  private static final GenericContainer<?> redisContainer =
      new GenericContainer<>(REDIS_IMAGE)
          .withCommand("redis-server", "--requirepass", REDIS_PASSWORD)
          .withExposedPorts(REDIS_PORT);

  static {
    redisContainer.start();
  }

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.redis.host", redisContainer::getHost);
    registry.add("spring.data.redis.port", () -> redisContainer.getMappedPort(REDIS_PORT));
    registry.add("spring.data.redis.password", () -> REDIS_PASSWORD);
  }

  @Test
  void shouldSaveValueToCache() {
    final var key = RandomString.make();
    final var value = RandomString.make();

    cacheUtility.save(key, value, Duration.ofSeconds(10));

    final var retrievedValue = cacheUtility.retrieve(key, String.class);

    assertThat(retrievedValue).isPresent().hasValue(value);
  }
}
