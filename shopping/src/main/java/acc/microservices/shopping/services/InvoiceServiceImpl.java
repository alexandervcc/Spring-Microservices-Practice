package acc.microservices.shopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import acc.microservices.shopping.exceptions.NotFoundException;
import acc.microservices.shopping.model.Invoice;
//import acc.microservices.shopping.repository.InvoiceItemRepository;
import acc.microservices.shopping.repository.InvoiceRepository;
import acc.microservices.shopping.services.interfaces.InvoiceService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
  private final InvoiceRepository invoiceRepository;
  // private final InvoiceItemRepository invoiceItemRepository;

  @Override
  public List<Invoice> findInvoiceAll() {
    return invoiceRepository.findAll();
  }

  @Override
  public Invoice createInvoice(Invoice invoice) {
    Optional<Invoice> invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
    if (invoiceDB.isPresent()) {
      throw new NotFoundException("Invoice with given ID already exists.");
    }
    invoice.setState("CREATED");
    return invoiceRepository.save(invoice);
  }

  @Override
  public Invoice updateInvoice(Invoice invoice) {
    Invoice invoiceDB = getInvoice(invoice.getId());
    invoiceDB.setCustomerId(invoice.getCustomerId());
    invoiceDB.setDescription(invoice.getDescription());
    invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
    invoiceDB.getItems().clear();
    invoiceDB.setItems(invoice.getItems());
    return invoiceRepository.save(invoiceDB);
  }

  @Override
  public Invoice deleteInvoice(Long invoiceId) {
    Invoice invoiceDB = getInvoice(invoiceId);
    invoiceDB.setState("DELETED");
    return invoiceRepository.save(invoiceDB);
  }

  @Override
  public Invoice getInvoice(Long id) {
    return invoiceRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Invoice NOT found for provided Id."));

  }
}
