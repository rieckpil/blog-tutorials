package de.rieckpil.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AzureConfigurationProperties.class)
public class AzureConfiguration {

	private final AzureConfigurationProperties azureConfigurationProperties;

	@Bean
	public BlobContainerClient blobContainerClient() {
		final var blobStorage = azureConfigurationProperties.getBlobStorage();
		final var connectionString = blobStorage.getConnectionString();
		final var containerName = blobStorage.getContainerName();

		final var blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
		return blobServiceClient.getBlobContainerClient(containerName);
	}

}