package de.rieckpil.helper;

import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

@Slf4j
public class AzureBlobStorageInitializer implements BeforeAllCallback {

  private static final String AZURITE_IMAGE = "mcr.microsoft.com/azure-storage/azurite:3.29.0";
  private static final GenericContainer<?> AZURITE_CONTAINER =
      new GenericContainer<>(AZURITE_IMAGE)
          .withCommand("azurite-blob", "--blobHost", "0.0.0.0")
          .withExposedPorts(10000);

  private static final String DEFAULT_AZURITE_CONNECTION_STRING =
      "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:%s/devstoreaccount1;";
  private static final String CONTAINER_NAME = RandomString.make().toLowerCase();
  private static String CONTAINER_CONNECTION_STRING;

  @Override
  public void beforeAll(final ExtensionContext context) {
    log.info("Creating Azurite container : {}", AZURITE_IMAGE);

    AZURITE_CONTAINER.start();
    initializeBlobContainer();
    addConfigurationProperties();

    log.info("Successfully started Azurite container : {}", AZURITE_IMAGE);
  }

  private void initializeBlobContainer() {
    final var blobPort = AZURITE_CONTAINER.getMappedPort(10000);
    CONTAINER_CONNECTION_STRING = String.format(DEFAULT_AZURITE_CONNECTION_STRING, blobPort);

    final var blobServiceClient =
        new BlobServiceClientBuilder().connectionString(CONTAINER_CONNECTION_STRING).buildClient();
    blobServiceClient.createBlobContainer(CONTAINER_NAME);
  }

  private void addConfigurationProperties() {
    System.setProperty("de.rieckpil.azure.blob-storage.container-name", CONTAINER_NAME);
    System.setProperty(
        "de.rieckpil.azure.blob-storage.connection-string", CONTAINER_CONNECTION_STRING);
  }
}
