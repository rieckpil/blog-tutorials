package de.rieckpil.blog;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class JAXRSConfiguration extends ResourceConfig {

	public JAXRSConfiguration() {
		packages("de.rieckpil.blog").register(MultiPartFeature.class);
	}

}
