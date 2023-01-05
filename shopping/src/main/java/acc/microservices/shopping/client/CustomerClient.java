package acc.microservices.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import acc.microservices.shopping.model.pojos.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {
  final String CUSTOMER_PATH = "/api/v1/customer";

  @GetMapping(value = CUSTOMER_PATH + "/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
