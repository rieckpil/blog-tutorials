package de.rieckpil.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().authenticated()
                )
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
                .build();
    }
}
