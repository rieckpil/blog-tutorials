package de.rieckpil.blog;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
  dataSourceLookup = "jdbc/__default",
  callerQuery = "SELECT password FROM user WHERE username = ?",
  groupsQuery = "SELECT role FROM user_roles where username = ?",
  hashAlgorithm = Pbkdf2PasswordHash.class
)
@BasicAuthenticationMechanismDefinition
@ApplicationScoped
@DeclareRoles({"USER", "ADMIN"})
public class ApplicationSecurityConfig {
}
