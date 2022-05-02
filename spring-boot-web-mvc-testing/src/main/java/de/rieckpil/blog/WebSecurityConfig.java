package de.rieckpil.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(
        authorizeRequests ->
          authorizeRequests.mvcMatchers(HttpMethod.POST, "/webhooks/orders").permitAll()
            .anyRequest().authenticated()
      )
      .csrf().disable();
  }
}
