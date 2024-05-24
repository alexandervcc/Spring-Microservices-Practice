package acc.microservices.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
    serverHttpSecurity.csrf(csrf -> csrf.disable())
        .authorizeExchange(exchange -> exchange
            // Allow request without token, if using this path for eureka resources
            .pathMatchers("/eureka/**").permitAll()
            // All other request need to be authenticated
            .anyExchange().authenticated());

    serverHttpSecurity.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

    return serverHttpSecurity.build();
  }
}
