package de.rieckpil.blog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class FileBoundary {

  private final FileEntityRepository fileEntityRepository;

  public FileBoundary(FileEntityRepository fileEntityRepository) {
    this.fileEntityRepository = fileEntityRepository;
  }

  @GetMapping
  public ResponseEntity<byte[]> getRandomFile() {

    long amountOfFiles = fileEntityRepository.count();
    Long randomPrimaryKey;

    if (amountOfFiles == 0) {
      return ResponseEntity.ok(new byte[0]);
    } else if (amountOfFiles == 1) {
      randomPrimaryKey = 1L;
    } else {
      randomPrimaryKey = ThreadLocalRandom.current().nextLong(1, amountOfFiles + 1);
    }

    FileEntity fileEntity = fileEntityRepository.findById(randomPrimaryKey).get();

    HttpHeaders header = new HttpHeaders();

    header.setContentType(MediaType.valueOf(fileEntity.getContentType()));
    header.setContentLength(fileEntity.getData().length);
    header.set("Content-Disposition", "attachment; filename=" + fileEntity.getFileName());

    return new ResponseEntity<>(fileEntity.getData(), header, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> uploadNewFile(@NotNull @RequestParam("file") MultipartFile multipartFile) throws IOException {

    FileEntity fileEntity = new FileEntity(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
      multipartFile.getBytes());

    fileEntityRepository.save(fileEntity);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    return ResponseEntity.created(location).build();

  }

}
