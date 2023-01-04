package acc.microservices.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.microservices.customer.model.Customer;
import acc.microservices.customer.model.Region;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  public Optional<Customer> findByCI(String CI);

  public List<Customer> findByLastName(String lastName);

  public List<Customer> findByRegion(Region region);
}
