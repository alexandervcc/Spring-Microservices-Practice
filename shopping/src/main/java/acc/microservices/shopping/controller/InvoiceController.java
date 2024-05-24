package acc.microservices.shopping.controller;

import acc.microservices.shopping.exceptions.ValidationErrorsException;
import acc.microservices.shopping.model.Invoice;
import acc.microservices.shopping.services.interfaces.InvoiceService;
// import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {
  private final InvoiceService invoiceService;

  @GetMapping(path = "")
  public ResponseEntity<List<Invoice>> listAllInvoices() {
    List<Invoice> invoices = this.invoiceService.findInvoiceAll();
    if (invoices.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(invoices);
  }

  @GetMapping(value = "/{id}")
  // Enable circuit breaker in this endpoint, using 'invoice' config from
  // application file. And fallback method when request fail
  // @CircuitBreaker(name = "invoice", fallbackMethod = "fallbackGetInvoice"). 
  // I think this one is used with RestTemplate
  public ResponseEntity<Invoice> getInvoice(@PathVariable("id") Integer id) {
    Invoice invoice = this.invoiceService.getInvoice(id);
    return ResponseEntity.ok(invoice);
  }

  @PostMapping(path = "")
  public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult validationResult) {
    if (validationResult.hasErrors()) {
      throw new ValidationErrorsException("Invoice data has errors.", validationResult);
    }
    Invoice invoiceDB = this.invoiceService.createInvoice(invoice);
    return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
  }

  public ResponseEntity<Invoice> fallbackGetInvoice(Invoice invoice, RuntimeException runtimeException) {
    Invoice failedInvoice = new Invoice();
    failedInvoice.setId(-1);
    failedInvoice.setDescription("Error creating invoice, try again later.");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(failedInvoice);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> updateInvoice(@PathVariable("id") Integer id, @RequestBody Invoice invoice) {
    invoice.setId(id);
    Invoice currentInvoice = this.invoiceService.updateInvoice(invoice);
    return ResponseEntity.ok(currentInvoice);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") Integer id) {
    Invoice invoice = this.invoiceService.deleteInvoice(id);
    return ResponseEntity.ok(invoice);
  }
}
