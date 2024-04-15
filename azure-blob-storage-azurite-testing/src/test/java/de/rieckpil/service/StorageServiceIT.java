package de.rieckpil.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.GenericContainer;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import lombok.SneakyThrows;
import net.bytebuddy.utility.RandomString;

@SpringBootTest
class StorageServiceIT {
	
	@Autowired
	private StorageService storageService;
	
    @Autowired
    private BlobContainerClient blobContainerClient;

    private static final String AZURITE_IMAGE = "mcr.microsoft.com/azure-storage/azurite:3.29.0";
	private static final GenericContainer<?> AZURITE_CONTAINER = new GenericContainer<>(AZURITE_IMAGE)
			.withCommand("azurite-blob", "--blobHost", "0.0.0.0")
			.withExposedPorts(10000);
    
	private static final String DEFAULT_AZURITE_CONNECTION_STRING = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:%s/devstoreaccount1;";
    private static final String CONTAINER_NAME = RandomString.make().toLowerCase();
    private static final String CONTAINER_CONNECTION_STRING;

    static {
        // Start Azurite Docker Container
    	AZURITE_CONTAINER.start();
        
        // Construct connection string for Azurite container
        final var blobPort = AZURITE_CONTAINER.getMappedPort(10000);
        CONTAINER_CONNECTION_STRING = String.format(DEFAULT_AZURITE_CONNECTION_STRING, blobPort);
    
        // Create blob container in Azurite
        final var blobServiceClient = new BlobServiceClientBuilder().connectionString(CONTAINER_CONNECTION_STRING).buildClient();
        blobServiceClient.createBlobContainer(CONTAINER_NAME);
    }
    
    @DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
    	registry.add("de.rieckpil.azure.blob-storage.container-name", () -> CONTAINER_NAME);
    	registry.add("de.rieckpil.azure.blob-storage.connection-string", () -> CONTAINER_CONNECTION_STRING);
	}
	
	@Test
	void shouldSaveBlobSuccessfullyToContainer() {
		// Prepare test file to upload
		final var blobName = RandomString.make() + ".txt";
		final var blobContent = RandomString.make(50);
		final var blobToUpload = createTextFile(blobName, blobContent);

		// Call method under test to save generated file
		storageService.save(blobToUpload);

		// Verify that the file is saved successfully by checking if it exists in the container
		final var blobClient = blobContainerClient.getBlobClient(blobName);
		assertThat(blobClient.exists()).isTrue();
		assertEquals(blobContent, new String(blobClient.downloadContent().toBytes()));
	}
    
	@Test
	@SneakyThrows
	void shouldFetchSavedBlobSuccessfullyFromContainerForValidBlobName() {
		// Prepare test file and upload to azure blob container
		final var blobName = RandomString.make() + ".txt";
		final var blobContent = RandomString.make(50);
		final var blobToUpload = createTextFile(blobName, blobContent);
		storageService.save(blobToUpload);

		// Retrieve the blob from the storage service using blob name
		final var retrievedBlob = storageService.retrieve(blobName);

		// Read the retrieved content and assert integrity
		assertEquals(blobContent, new String(retrievedBlob.getContentAsByteArray()));
	}
    
	@Test
	void fetchShouldThrowExceptionForNonExistentBlobName() {
		// Generate a random blob name
		final var blobName = RandomString.make() + ".txt";

		// call method under test and assert exception
		final var exception = assertThrows(IllegalArgumentException.class, () -> storageService.retrieve(blobName));
		assertThat(exception.getMessage()).isEqualTo("No blob exists with given blob name");
	}
	
	@Test
	@SneakyThrows
	void shouldDeleteBlobFromContainerSuccessfully() {
		// Prepare test file and upload to azure blob container
		final var blobName = RandomString.make() + ".txt";
		final var blobContent = RandomString.make(50);
		final var blobToUpload = createTextFile(blobName, blobContent);
		storageService.save(blobToUpload);

		// Verify that the blob has been saved in the container
		final var retrievedBlob = storageService.retrieve(blobName);
		assertThat(retrievedBlob).isNotNull();
		assertEquals(blobContent, new String(retrievedBlob.getContentAsByteArray()));

		// Invoke method under test to delete Blob from container
		storageService.delete(blobName);

		// Verify that the blob does not exist in the container post deletion
		final var exception = assertThrows(IllegalArgumentException.class, () -> storageService.retrieve(blobName));
		assertThat(exception.getMessage()).isEqualTo("No blob exists with given blob name");
	}
    
	@SneakyThrows
	private MultipartFile createTextFile(final String fileName, final String content) {
		final var fileContentBytes = content.getBytes();
		final var inputStream = new ByteArrayInputStream(fileContentBytes);
		return new MockMultipartFile(fileName, fileName, "text/plain", inputStream);
	}

}