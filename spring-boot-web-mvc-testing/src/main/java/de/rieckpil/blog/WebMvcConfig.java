package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  private final String validApiKey;

  public WebMvcConfig(@Value("${valid-api-key}") String validApiKey) {
    this.validApiKey = validApiKey;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new WebhookAuthorizationHandlerInterceptor(validApiKey))
      .addPathPatterns("/webhooks/**");
  }
}
