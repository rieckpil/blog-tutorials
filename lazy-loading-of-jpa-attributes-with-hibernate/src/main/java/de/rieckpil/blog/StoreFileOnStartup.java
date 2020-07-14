package de.rieckpil.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;

@Component
@Order(1)
@Slf4j
public class StoreFileOnStartup implements CommandLineRunner {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    File file = new File(getClass().getClassLoader().getResource("application.properties").getFile());
    byte[] fileBytes = new byte[(int) file.length()];
    FileInputStream fis = new FileInputStream(file);
    fis.read(fileBytes);

    FileUpload fileUpload = new FileUpload();
    fileUpload.setFileName(file.getName());
    fileUpload.setFileType("Property file");
    fileUpload.setFileContent(fileBytes);

    entityManager.persist(fileUpload);
    entityManager.flush();

    log.info("--- File successfully stored to the database");

  }
}
