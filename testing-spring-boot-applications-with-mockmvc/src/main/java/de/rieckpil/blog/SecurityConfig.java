package de.rieckpil.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.GET, "/dashboard")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/tasks/**")
                    .authenticated()
                    .requestMatchers("/api/users/**")
                    .permitAll()
                    .requestMatchers("/**")
                    .authenticated())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
