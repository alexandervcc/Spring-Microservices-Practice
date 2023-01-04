package acc.microservices.customer.services.interfaces;

import java.util.List;

import acc.microservices.customer.model.Customer;
import acc.microservices.customer.model.Region;

public interface CustomerService {
  public List<Customer> findCustomerAll();

  public List<Customer> findCustomersByRegion(Region region);

  public Customer createCustomer(Customer customer);

  public Customer updateCustomer(Customer customer);

  public Customer deleteCustomer(Long customerId);

  public Customer getCustomer(Long id);


}
