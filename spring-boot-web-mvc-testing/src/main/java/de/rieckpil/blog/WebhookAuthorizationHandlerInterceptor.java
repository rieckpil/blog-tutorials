package de.rieckpil.blog;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class WebhookAuthorizationHandlerInterceptor implements HandlerInterceptor {

  private final String validApiKey;

  private static final Logger LOG = LoggerFactory.getLogger(WebhookAuthorizationHandlerInterceptor.class);

  public WebhookAuthorizationHandlerInterceptor(String validApiKey) {
    this.validApiKey = validApiKey;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String apiKey = request.getHeader("X-API-KEY");

    LOG.debug("Incoming X-API-KEY header for accessing a webhook: '{}'", apiKey);

    if (validApiKey.equalsIgnoreCase(apiKey)) {
      return true;
    } else {
      LOG.warn("Invalid API key in the request when trying to access webhooks: '{}'", apiKey);
      response.sendError(403); // you may also argue to return 401 here
      return false;
    }
  }
}
