package de.rieckpil.blog;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("resources")
@DeclareRoles({"USER", "ADMIN"})
public class JAXRSConfiguration extends Application {

}
