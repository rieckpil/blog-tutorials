package de.rieckpil.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileHandlingController {

  @GetMapping
  public void download() {

  }

  @PostMapping
  public void upload() {

  }
}
