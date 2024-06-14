package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import de.rieckpil.configuration.JwtConfigurationProperties;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@InitializeSecretKey
@InitializeMysqlContainer
@InitializeRedisContainer
@EnableConfigurationProperties(JwtConfigurationProperties.class)
class AuthServiceIT {

  @Autowired private Validator validator;

  @Autowired private JwtConfigurationProperties jwtConfigurationProperties;

  @Test
  void jwtSecretKeyIsConfiguredCorrectly() {
    final var violations = validator.validate(jwtConfigurationProperties);
    assertThat(violations).isEmpty();
  }
}
