package acc.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("product-service", r -> r.path("/api/product/**")
						.filters(f -> f.stripPrefix(2))
						.uri("lb://PRODUCT-SERVICE"))
				.route("customer-service", r -> r.path("/api/customer/**")
						.filters(f -> f.stripPrefix(2))
						.uri("lb://CUSTOMER-SERVICE"))
				.route("shopping-service", r -> r.path("/api/shopping/**")
						.filters(f -> f.stripPrefix(2))
						.uri("lb://SHOPPING-SERVICE"))
				// Main eureka dashboard
				.route("eureka-service", p -> p.path("/eureka/web/**")
						.filters(f -> f.stripPrefix(2))
						.uri("http://localhost:8099"))
				// Static eureka files: CSS, JS
				.route("eureka-service", p -> p.path("/eureka/**")
						.uri("http://localhost:8099"))

				.build();

	}

}
