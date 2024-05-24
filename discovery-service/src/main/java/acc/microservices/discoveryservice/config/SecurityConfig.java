package acc.microservices.discoveryservice.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private String username = "eureka-admin";
  private String password = "eureka-pass";

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request
            .anyRequest()
            .authenticated());

    http.httpBasic(withDefaults());

    return http.build();
  }

  @Bean
  UserDetailsManager userDetailService() {
    @SuppressWarnings("deprecation")
    UserDetails eurekaAdmin = User.withDefaultPasswordEncoder()
        .username(username)
        .password(password)
        .roles("ADMIN")
        .build();

    InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
    users.createUser(eurekaAdmin);

    return users;
  }
}
