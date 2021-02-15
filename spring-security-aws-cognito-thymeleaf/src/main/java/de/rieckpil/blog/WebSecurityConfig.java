package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final String clientId;
  private final String logoutUrl;

  public WebSecurityConfig(@Value("${spring.security.oauth2.client.registration.cognito.clientId}") String clientId,
                           @Value("${cognito.logoutUrl}") String logoutUrl) {
    this.clientId = clientId;
    this.logoutUrl = logoutUrl;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .and()
      .authorizeRequests(authorize ->
        authorize.mvcMatchers("/").permitAll()
          .anyRequest().authenticated())
      .oauth2Login()
      .and()
      .logout()
      .logoutSuccessHandler(new CognitoOidcLogoutSuccessHandler(logoutUrl, clientId));
  }
}
