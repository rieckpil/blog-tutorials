package de.rieckpil.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig  {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.GET, "/dashboard").permitAll()
        .requestMatchers("/api/users/**").authenticated()
        .requestMatchers("/**").authenticated()
      )
      .httpBasic(withDefaults());

    return http.build();
  }
}
