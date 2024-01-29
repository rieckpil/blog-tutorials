package de.rieckpil.blog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class ReadFileOnStartup implements CommandLineRunner {

  @PersistenceContext private EntityManager entityManager;

  @Transactional
  @Override
  public void run(String... args) throws Exception {

    log.info("--- Loading file from database");

    FileUpload allFileUploads = entityManager.find(FileUpload.class, 1L);

    log.info("--- File successfully loaded from database");

    log.info("--- Accessing fileContent");

    byte[] content = allFileUploads.getFileContent();

    log.info("--- the file has: " + content.length + " bytes");
  }
}
