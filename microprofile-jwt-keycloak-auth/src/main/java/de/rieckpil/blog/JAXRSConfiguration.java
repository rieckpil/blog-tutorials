package de.rieckpil.blog;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@LoginConfig(authMethod = "MP-JWT", realmName = "JWT-JEE8")
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {

}
