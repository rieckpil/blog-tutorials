package de.rieckpil.blog;

import de.rieckpil.blog.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class RateLimitingFilter implements ContainerRequestFilter {

  @PersistenceContext
  EntityManager entityManager;

  @Transactional
  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    SecurityContext securityContext = requestContext.getSecurityContext();
    String username = securityContext.getUserPrincipal().getName();

    User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class).setParameter(
      "username", username).getSingleResult();

    if (user.getAmountOfApiCalls() >= user.getMaxApiCallsPerMinute()) {
      requestContext.abortWith(Response.status(Response.Status.TOO_MANY_REQUESTS).build());
    }

    user.setAmountOfApiCalls(user.getAmountOfApiCalls() + 1);
    System.out.println(user);
  }
}
