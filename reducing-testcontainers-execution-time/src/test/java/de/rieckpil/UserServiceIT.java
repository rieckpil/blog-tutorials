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

@SpringBootTest
@InitializeRedisContainer
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class UserServiceIT {

  @Autowired private CacheUtility cacheUtility;

  @Test
  void shouldSaveValueToCache() {
    final var key = RandomString.make();
    final var value = RandomString.make();

    cacheUtility.save(key, value, Duration.ofSeconds(10));

    final var retrievedValue = cacheUtility.retrieve(key, String.class);

    assertThat(retrievedValue).isPresent().hasValue(value);
  }
}
