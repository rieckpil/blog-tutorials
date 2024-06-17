package de.rieckpil.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "de.rieckpil.jwt")
public class JwtConfigurationProperties {

  @NotBlank(message = "Secret key must not be configured")
  @Pattern(regexp = "^[a-zA-Z0-9+/]*={0,2}$", message = "Secret key must be Base64 encoded.")
  private String secretKey;
}
