package acc.microservices.shopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import acc.microservices.shopping.model.pojos.Customer;

//Fallback class for Feign Client errors
@Component
public class CustomerHystrixFallback implements CustomerClient {

  @Override
  public ResponseEntity<Customer> getCustomer(long id) {
    Customer customer = Customer.builder().id(id).build();
    return ResponseEntity.ok(customer);
  }

}
