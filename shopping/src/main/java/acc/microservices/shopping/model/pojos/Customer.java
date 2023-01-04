package acc.microservices.shopping.model.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private Long id;
  private String CI;
  private String firstName;
  private String lastName;
  private String email;
  private Region region;
  private String state;
}
