package acc.microservices.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.microservices.customer.exceptions.ValidationErrorsException;
import acc.microservices.customer.model.Customer;
import acc.microservices.customer.model.Region;
import acc.microservices.customer.services.interfaces.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping(path = "")
  public ResponseEntity<List<Customer>> listAllCustomers(
      @RequestParam(name = "regionId", required = false) Long regionId) {
    List<Customer> customers = null;
    if (null == regionId) {
      customers = customerService.findCustomerAll();
    } else {
      customers = customerService.findCustomersByRegion(Region.builder().id(regionId).build());
    }
    if (customers.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(customers);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
    Customer customer = customerService.getCustomer(id);
    return ResponseEntity.ok(customer);
  }

  @PostMapping(path = "")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customerData,
      BindingResult validationResult) {
    if (validationResult.hasErrors()) {
      throw new ValidationErrorsException("Product attribute(s) are invalid.", validationResult);
    }

    Customer customer = customerService.createCustomer(customerData);

    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> updateCustomer(@PathVariable("id") long id,
      @RequestBody(required = true) Customer customer) {
    Customer currentCustomer = customerService.getCustomer(id);
    currentCustomer = customerService.updateCustomer(customer);
    return ResponseEntity.ok(currentCustomer);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
    Customer customer = this.customerService.deleteCustomer(id);
    return ResponseEntity.ok(customer);
  }
}
