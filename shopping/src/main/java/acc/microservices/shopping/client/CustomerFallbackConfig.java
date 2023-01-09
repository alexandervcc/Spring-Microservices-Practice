package acc.microservices.shopping.client;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class CustomerFallbackConfig {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> circuitBreakerFactoryCustomizer() {
    CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
        .slidingWindowSize(5)
        .failureRateThreshold(20.0f)
        .waitDurationInOpenState(Duration.ofSeconds(5))
        .permittedNumberOfCallsInHalfOpenState(5)
        .build();
    return resilience4JCircuitBreakerFactory -> resilience4JCircuitBreakerFactory
        .configure(builder -> builder.circuitBreakerConfig(cbConfig), "CustomerClient#getCustomer(id)");

  }
}
