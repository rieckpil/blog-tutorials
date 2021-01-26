package de.rieckpil.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .httpBasic()
      .and()
      .authorizeRequests(
        requests -> requests
          .mvcMatchers(HttpMethod.GET, "/api/books").permitAll()
          .mvcMatchers(HttpMethod.GET, "/api/books/*").permitAll()
          .mvcMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN")
          .anyRequest().authenticated());
  }
}
