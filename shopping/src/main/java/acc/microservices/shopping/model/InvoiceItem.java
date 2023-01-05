package acc.microservices.shopping.model;

import acc.microservices.shopping.model.pojos.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class InvoiceItem {

  public InvoiceItem() {
    this.quantity = (int) 0;
    this.price = (double) 0;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Positive(message = "El stock debe ser mayor que cero.")
  private Integer quantity;

  @Positive(message = "El precio debe ser mayor que cero.")
  private Double price;

  private Long productId;

  @Transient
  private Double subTotal;

  @Transient
  private Product product;

  public Double getSubTotal() {
    if (this.price > 0 && this.quantity > 0) {
      return this.quantity * this.price;
    }
    return (double) 0;
  }

}
