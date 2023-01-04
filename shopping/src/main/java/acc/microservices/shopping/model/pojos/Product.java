package acc.microservices.shopping.model.pojos;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
  private Long id;
  private String name;
  private String description;
  private Double price;
  private Integer stock;
  private String status;
  private Date createdAt;
  private Category category;
}
