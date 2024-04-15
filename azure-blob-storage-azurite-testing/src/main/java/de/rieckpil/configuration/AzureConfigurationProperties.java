package de.rieckpil.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "de.rieckpil.azure")
public class AzureConfigurationProperties {

  @Valid private BlobStorage blobStorage = new BlobStorage();

  @Getter
  @Setter
  @Validated
  public class BlobStorage {

    @NotBlank(message = "Azure Blob Storage container name must be configured")
    private String containerName;

    @NotBlank(message = "Azure Blob Storage connection string must be configured")
    private String connectionString;
  }
}
