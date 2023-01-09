package acc.microservices.shopping.client;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import acc.microservices.shopping.model.pojos.Customer;

@Configuration
public class CustomerFallbackConfig implements CustomerClient {

	@Override
	public ResponseEntity<Customer> getCustomer(long id) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Customer.builder().id(0L).firstName("Default User").build());
	}

}
