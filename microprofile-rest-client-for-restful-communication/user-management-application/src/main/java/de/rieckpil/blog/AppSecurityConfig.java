package de.rieckpil.blog;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@ApplicationScoped
@BasicAuthenticationMechanismDefinition
@DeclareRoles("ADMIN")
public class AppSecurityConfig {
}
