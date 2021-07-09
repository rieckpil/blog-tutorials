package de.rieckpil.blog;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .mvcMatchers(HttpMethod.GET, "/api/customers").permitAll()
      .mvcMatchers(HttpMethod.GET, "/api/customers/*").authenticated()
      .mvcMatchers(HttpMethod.GET, "/dashboard").permitAll()
      .requestMatchers(EndpointRequest.to(HealthEndpoint.class)).permitAll()
      .anyRequest().authenticated()
    .and()
    .httpBasic();
  }
}
