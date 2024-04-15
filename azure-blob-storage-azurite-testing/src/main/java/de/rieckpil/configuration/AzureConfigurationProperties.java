package de.rieckpil.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Maps configuration values defined in the active {@code .yml} file to the corresponding instance
 * variables below. The configuration properties are referenced within the application to facilitate
 * connection with provisioned Azure Blob Storage Container.
 *
 * <p><b> Example YAML configuration: </b>
 *
 * <pre>
 * de:
 *   rieckpil:
 *     azure:
 *       blob-storage:
 *         container-name: "container-name-goes-here"
 *         connection-string: "connection-string-goes-here"
 * </pre>
 *
 * @see AzureConfiguration
 */
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
