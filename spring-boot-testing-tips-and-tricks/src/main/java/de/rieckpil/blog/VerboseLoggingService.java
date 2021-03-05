package de.rieckpil.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VerboseLoggingService {

  private static final Logger LOG = LoggerFactory.getLogger(VerboseLoggingService.class);

  public void notify(String message) {
    System.out.println(message);
    LOG.info(message);

    System.err.println("Error");
  }
}
