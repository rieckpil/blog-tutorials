package de.rieckpil.blog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileHandlingController {

  @GetMapping
  public ResponseEntity<byte[]> download() throws IOException {

    byte[] pdfContent = this.getClass().getResourceAsStream("/document.pdf").readAllBytes();

    HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_PDF);
    header.setContentLength(pdfContent.length);
    header.set("Content-Disposition", "attachment; filename=document.pdf");

    return new ResponseEntity(pdfContent, header, HttpStatus.OK);
  }

  @PostMapping
  public void upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

    System.out.println("Uploaded new file");
    System.out.println("Filename: " + multipartFile.getOriginalFilename());
    System.out.println("Size: " + multipartFile.getSize());

    byte[] content = multipartFile.getBytes();

  }
}
