package acc.microservices.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.microservices.shopping.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
  public List<Invoice> findByCustomerId(Long customerId);

  public Invoice findByNumberInvoice(String numberInvoice);
}
