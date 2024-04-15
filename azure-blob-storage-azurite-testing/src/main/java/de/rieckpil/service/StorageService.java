package de.rieckpil.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageService {

  private final BlobContainerClient blobContainerClient;

  @SneakyThrows
  public void save(@NonNull final MultipartFile file) {
    final var blobName = file.getOriginalFilename();
    final var blobClient = blobContainerClient.getBlobClient(blobName);
    blobClient.upload(file.getInputStream());
  }

  public InputStreamResource retrieve(@NonNull final String blobName) {
    final var blobClient = getBlobClient(blobName);
    final var inputStream = blobClient.downloadContent().toStream();
    return new InputStreamResource(inputStream);
  }

  public void delete(@NonNull final String blobName) {
    final var blobClient = getBlobClient(blobName);
    blobClient.delete();
  }

  private BlobClient getBlobClient(@NonNull final String blobName) {
    final var blobClient = blobContainerClient.getBlobClient(blobName);
    if (Boolean.FALSE.equals(blobClient.exists())) {
      throw new IllegalArgumentException("No blob exists with given blob name");
    }
    return blobClient;
  }
}
