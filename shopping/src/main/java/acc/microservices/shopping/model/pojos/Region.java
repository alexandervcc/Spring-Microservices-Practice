package acc.microservices.shopping.model.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Region {
  private Long id;
  private String name;
}
