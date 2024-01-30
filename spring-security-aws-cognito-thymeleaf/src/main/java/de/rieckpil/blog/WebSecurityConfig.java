package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

  private final String clientId;
  private final String logoutUrl;

  public WebSecurityConfig(
      @Value("${spring.security.oauth2.client.registration.cognito.clientId}") String clientId,
      @Value("${cognito.logoutUrl}") String logoutUrl) {
    this.clientId = clientId;
    this.logoutUrl = logoutUrl;
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.csrf(Customizer.withDefaults())
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/").permitAll().anyRequest().authenticated())
        .oauth2Login(Customizer.withDefaults())
        .logout(
            logout ->
                logout.logoutSuccessHandler(
                    new CognitoOidcLogoutSuccessHandler(logoutUrl, clientId)));

    return http.build();
  }
}
