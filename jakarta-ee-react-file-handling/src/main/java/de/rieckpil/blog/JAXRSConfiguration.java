package de.rieckpil.blog;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class JAXRSConfiguration extends ResourceConfig {
  public JAXRSConfiguration() {
    packages("de.rieckpil.blog").register(MultiPartFeature.class);
  }
}
