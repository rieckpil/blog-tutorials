package de.rieckpil.blog;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    private Set<String> defaultRoles = new HashSet<>(Arrays.asList("ADMIN"));

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {

        if (credential.getPassword().compareTo("SECRET")) {
            return new CredentialValidationResult(credential.getCaller(), defaultRoles);
        }
        return INVALID_RESULT;
    }
}
