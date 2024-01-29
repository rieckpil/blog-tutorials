package de.rieckpil.blog;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("custom-liveness")
public class CustomLivenessCheck implements HealthIndicator {

  @Override
  public Health health() {
    if (checkLiveness()) {
      return Health.up().build();
    }
    return Health.down().withDetail("Error Code", 42).build();
  }

  private boolean checkLiveness() {
    return ThreadLocalRandom.current().nextBoolean();
  }
}
