package acc.microservices.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import acc.microservices.customer.exceptions.NotFoundException;
import acc.microservices.customer.model.Customer;
import acc.microservices.customer.model.Region;
import acc.microservices.customer.repository.CustomerRepository;
import acc.microservices.customer.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public List<Customer> findCustomersByRegion(Region region) {
    return this.customerRepository.findByRegion(region);
  }

  @Override
  public Customer createCustomer(Customer customer) {
    Optional<Customer> oldCustomer = this.customerRepository.findByCI(customer.getCI());
    if (oldCustomer.isPresent()) {
      throw new NotFoundException("Provided CI already in use.");
    }
    customer.setState("CREATED");
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    Customer customerDB = this.getCustomer(customer.getId());

    customerDB.setFirstName(customer.getFirstName());
    customerDB.setLastName(customer.getLastName());
    customerDB.setEmail(customer.getEmail());

    return customerRepository.save(customerDB);
  }

  @Override
  public Customer deleteCustomer(Long customerId) {
    Customer customerDB = this.getCustomer(customerId);

    customerDB.setState("DELETED");
    return customerRepository.save(customerDB);
  }

  @Override
  public Customer getCustomer(Long id) {
    return customerRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Customer NOT found for provided Id."));
  }

  @Override
  public List<Customer> findCustomerAll() {
    return this.customerRepository.findAll();
  }

}
