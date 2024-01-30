package de.rieckpil.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityWebFilterChain configure(ServerHttpSecurity http) {
    http.authorizeExchange(
            authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().authenticated())
        .oauth2Client(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults());

    return http.build();
  }
}
